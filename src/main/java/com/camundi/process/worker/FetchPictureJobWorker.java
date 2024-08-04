package com.camundi.process.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.camundi.process.model.Picture;
import com.camundi.process.repository.PictureRepository;
import com.camundi.process.service.ImageSearchService;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

@Component
public class FetchPictureJobWorker {

	@Autowired
	private PictureRepository pictureRepository;

	@Autowired
	private ImageSearchService imageSearchService;

	@JobWorker(type = "fetch-picture")
	public void fetchPicture(final ActivatedJob job) {
		String animalType = (String) job.getVariablesAsMap().get("animalType");
		Long processInstanceKey = job.getProcessInstanceKey();

		// Search for image & save
		imageSearchService.searchImage(animalType).subscribe(imageUrl -> {
			Picture picture = new Picture();
			picture.setProcessInstanceKey(processInstanceKey);
			picture.setAnimalType(animalType);
			picture.setUrl(imageUrl);
			pictureRepository.save(picture);
		});

	}
}
