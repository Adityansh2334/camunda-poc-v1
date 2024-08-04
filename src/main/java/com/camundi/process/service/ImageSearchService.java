package com.camundi.process.service;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@Service
public class ImageSearchService {

	@Value("${google.api.key}")
	private String apiKey;

	@Value("${google.cse.id}")
	private String cseId;
	
	@Value("${animal.image.size}")
	private String imageSize;

	private final WebClient webClient;

	public ImageSearchService(WebClient.Builder webClientBuilder) {
		this.webClient = webClientBuilder.baseUrl("https://www.googleapis.com/customsearch/v1").build();
	}

	public Mono<String> searchImage(String query) {
        // Generate a random stratIndex to ensure different results
		int startIndex = (int) (Math.random() * 91) + 1;
        startIndex = startIndex - (startIndex % 10); // Ensure multiple of 10

        return makeRequest(query,imageSize,startIndex)
                .retryWhen(Retry.fixedDelay(3, Duration.ofSeconds(2))
                        .filter(this::isRetryable))
                .switchIfEmpty(Mono.error(new RuntimeException("No image URL found after retries")));
    }

    private Mono<String> makeRequest(String query, String imgSize, int startIndex) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", query)
                        .queryParam("cx", cseId)
                        .queryParam("key", apiKey)
                        .queryParam("searchType", "image")
                        .queryParam("imgSize", imgSize)
                        .queryParam("start", startIndex) // Add random startIndex
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(this::extractImageUrl)
                .filter(url -> url != null && !url.isEmpty()); // Ensure URL is present
    }

    private boolean isRetryable(Throwable throwable) {
        return throwable instanceof RuntimeException; 
    }

	private String extractImageUrl(String response) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(response);
			JsonNode items = rootNode.path("items");
			if (items.isArray()) {
				for (JsonNode item : items) {
					JsonNode image = item.path("link");
					if (!image.isMissingNode()
							&& (image.asText().endsWith(".jpg") || image.asText().endsWith(".jpeg"))) {
						return image.asText();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
