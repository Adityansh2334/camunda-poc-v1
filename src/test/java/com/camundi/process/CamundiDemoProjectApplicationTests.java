package com.camundi.process;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.DeploymentEvent;
import io.camunda.zeebe.process.test.api.ZeebeTestEngine;
import io.camunda.zeebe.process.test.assertions.BpmnAssert;
import io.camunda.zeebe.process.test.assertions.DeploymentAssert;
import io.camunda.zeebe.process.test.filters.RecordStream;
import io.camunda.zeebe.spring.test.ZeebeSpringTest;

@SpringBootTest
@ZeebeSpringTest
public class CamundiDemoProjectApplicationTests {

	@Autowired
	private ZeebeTestEngine engine;

	@Autowired
	private ZeebeClient client;

	@Autowired
	private RecordStream recordStream;

	@Test
	void testProcessInstance() {
		// Deploy the process definition
		DeploymentEvent event = 
				client.newDeployCommand()
				.addResourceFromClasspath("animal-picture-process.bpmn")
				.send()
				.join();
		DeploymentAssert assertions = BpmnAssert.assertThat(event);
	}
}
