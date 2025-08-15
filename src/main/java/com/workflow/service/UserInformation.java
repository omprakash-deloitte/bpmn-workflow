package com.workflow.service;

import com.workflow.model.User;
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

    private final String apiUrl = "http://localhost:8081/user/USER_ID";

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String newApiUrl = apiUrl.replace("USER_ID", String.valueOf(123));
        ResponseEntity<User> response = restTemplate.getForEntity(newApiUrl, User.class);
        System.out.println("Response of user info : " + response.getBody());
        execution.setVariable("loanAmount", 1100000);
        execution.setVariable("userName", "Om Prakash");
        execution.setVariable("address", "XYZ india");
        execution.setVariable("userId", 123);
        execution.setVariable("creditScore", 750);
        execution.setVariable("age", 19);
        execution.setVariable("gender", "Male");
        execution.setVariable("phoneNumber", "+91-1234567890");
    }
}
