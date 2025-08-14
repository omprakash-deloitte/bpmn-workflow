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

    private final String apiUrl = "http://localhost:8081/loan/create-request?userName=Om%20Prakash";

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Application Name : " + execution.getVariable("applicationName"));
        System.out.println("Loan Date : " + execution.getVariable("loanDate"));
        System.out.println("Loan Amount : " + execution.getVariable("loanAmount"));
        System.out.println("User Details : " + execution.getVariable("userDetails"));


        HttpEntity<String> httpEntity = new HttpEntity<>("");
        ResponseEntity<?> response = restTemplate.postForEntity(apiUrl,httpEntity,String.class);
        System.out.println("Rest Template response " + response.getBody());

    }
}
