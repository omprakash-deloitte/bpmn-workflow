package com.workflow.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class NotifyManager implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println();
        System.out.println("--------------------- ALERT MANAGER -----------------------");
        System.out.println("User : " + execution.getVariable("userName") + " with user ID : " + execution.getVariable("userId") + " is not completed within specified time period");
        System.out.println("--------------------- ALERT MANAGER -----------------------");
        System.out.println();

//        call rest api to notify manager;
    }
}
