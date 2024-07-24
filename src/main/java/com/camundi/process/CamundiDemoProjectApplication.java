package com.camundi.process;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.spring.client.annotation.Deployment;

@SpringBootApplication
@Deployment(resources = "classpath:animal-picture-process.bpmn")
public class CamundiDemoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundiDemoProjectApplication.class, args);
	}

}
