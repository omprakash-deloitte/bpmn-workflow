package com.workflow.controller;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflow")
public class BpmnController {

    @GetMapping("/execute")
    public String executeWorkflow(){

        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstantiationBuilder instant = engine.getRuntimeService().createProcessInstanceByKey("process-logger");

        instant.executeWithVariablesInReturn();

        return "Workflow started.";
    }
}
