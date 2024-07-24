package com.camundi.process.worker;


import com.camundi.process.model.Picture;
import com.camundi.process.repository.PictureRepository;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class FetchPictureJobWorker {

    @Autowired
    private PictureRepository pictureRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    @JobWorker(type = "fetch-picture")
    public void fetchPicture(final ActivatedJob job) {
        String animalType = (String) job.getVariablesAsMap().get("animalType");
        Long processInstanceKey = job.getProcessInstanceKey();
        String url;

        switch (animalType) {
            case "cat":
                url = "https://placekitten.com/200/300";
                break;
            case "dog":
                url = "https://place.dog/200/300";
                break;
            case "bear":
                url = "https://placebear.com/200/300";
                break;
            default:
                throw new IllegalArgumentException("Unknown animal type: " + animalType);
        }

        Picture picture = new Picture();
        picture.setProcessInstanceKey(processInstanceKey);
        picture.setAnimalType(animalType);
        picture.setUrl(url);

        pictureRepository.save(picture);
    }
}
