package com.workflow.controller;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/workflow")
public class BpmnController {

    @GetMapping("/execute/{userId}")
    public String executeWorkflow(@PathVariable("userId") Integer userId){

        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstantiationBuilder instant = engine.getRuntimeService().createProcessInstanceByKey("process-logger");

        instant.setVariable("userId",userId);
        instant.executeWithVariablesInReturn();

        return "Workflow started.";
    }
}
