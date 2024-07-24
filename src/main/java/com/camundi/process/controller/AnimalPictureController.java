package com.camundi.process.controller;

import com.camundi.process.service.AnimalPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pictures")
public class AnimalPictureController {

    @Autowired
    private AnimalPictureService animalPictureService;

    @PostMapping("/start")
    public String startProcess(@RequestParam String animalType) {
        return animalPictureService.startProcess(animalType);
    }

    @GetMapping("/{id}")
    public String getPicture(@PathVariable Long id) {
        return animalPictureService.getPicture(id);
    }
}

