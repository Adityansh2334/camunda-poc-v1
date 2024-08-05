package com.camundi.process.service;


import com.camundi.process.model.Picture;
import com.camundi.process.repository.PictureRepository;
import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalPictureService {

    @Autowired
    private ZeebeClient zeebeClient;

    @Autowired
    private PictureRepository pictureRepository;

    public String startProcess(String animalType) {
        ProcessInstanceEvent event = zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId("animal-picture-process")
                .latestVersion()
                .variables("{\"animalType\": \"" + animalType + "\"}")
                .send()
                .join();

        return event.getProcessInstanceKey()+"";
    }

    public String getPicture(Long processInstanceKey) {
        Optional<Picture> picture = pictureRepository.findByProcessInstanceKey(processInstanceKey);
        if(picture.isEmpty()) {
        	return "";
        }
        return picture.get().getUrl();
    }
}

