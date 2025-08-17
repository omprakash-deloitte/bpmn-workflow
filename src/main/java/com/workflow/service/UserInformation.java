package com.workflow.service;

import com.workflow.model.User;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@Service
public class UserInformation implements JavaDelegate {

    @Autowired
    private RestTemplate restTemplate;

    private final String apiUrl = "http://localhost:8081/user/";

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Integer id = (Integer) execution.getVariable("userId");
        String newApiUrl = apiUrl + id;

        try {
            ResponseEntity<User> response = restTemplate.getForEntity(newApiUrl, User.class);
            User user = response.getBody();
            if (user != null) {
                execution.setVariable("userId", user.getUserId());
                execution.setVariable("userName", user.getUserName());
                execution.setVariable("age", user.getAge());
                execution.setVariable("gender", user.getGender());
                execution.setVariable("phoneNumber", user.getPhoneNumber());
                execution.setVariable("loanAmount", user.getLoanAmount());
                execution.setVariable("address", user.getAddress());
                execution.setVariable("creditScore", user.getCreditScore());
            } else {
                System.out.println("USER is null.");
            }

            System.out.println();
            System.out.println("********************************************");
            System.out.println("User Information fetched");
            System.out.println("********************************************");
            System.out.println();

        } catch (Exception e) {
            Integer retries = (Integer) execution.getVariable("retriesLeft");

            if (retries == null) {
                retries = 2; // total 3 attempts: 2 remaining after first failure
            } else {
                retries = retries - 1;
            }

            execution.setVariable("retriesLeft", retries);

            System.out.println("retries left : " + retries);

            if (retries <= 0) {
                System.out.println("throwing bpmn errro ::: ");
                throw new BpmnError("REST_API_FAILED", "Max retries reached.");
            } else {
                System.out.println("retries left : " + retries);
                throw e; // Let Camunda retry
            }
        }
    }
}