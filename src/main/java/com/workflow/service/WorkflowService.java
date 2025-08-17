package com.workflow.service;

import com.workflow.model.User;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WorkflowService implements JavaDelegate {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println();
        System.out.println("=============================================");
        System.out.println("Application Name : " + execution.getVariable("applicationName"));
        System.out.println("User Name : " + execution.getVariable("userName"));
        System.out.println("User Id : " + execution.getVariable("userId"));
        System.out.println("Loan Amount : " + execution.getVariable("loanAmount"));
        System.out.println("Credit Score : " + execution.getVariable("creditScore"));
        System.out.println("User Details : " + execution.getVariable("userDetails"));
        System.out.println("=============================================");
        System.out.println();
    }
}
