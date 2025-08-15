package com.workflow.service;

import com.workflow.model.LoanRequest;
import com.workflow.model.User;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApprovalEmail implements JavaDelegate {

    @Autowired
    private RestTemplate restTemplate;

    private final String approvalUrl = "http://localhost:8081/loan/send-email";

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        User user = new User();
        user.setUserName((String) execution.getVariable("userName"));
        user.setUserId((Integer) execution.getVariable("userId"));
        user.setAddress((String) execution.getVariable("address"));
        user.setAge((Integer) execution.getVariable("age"));
        user.setCreditScore((Integer) execution.getVariable("creditScore"));
        user.setGender((String) execution.getVariable("gender"));
        user.setPhoneNumber((String) execution.getVariable("phoneNumber"));
        user.setLoanAmount((Integer) execution.getVariable("loanAmount"));

        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setEligibleForLoan(true);
        loanRequest.setUser(user);

        HttpEntity<LoanRequest> httpEntity = new HttpEntity<>(loanRequest);
        ResponseEntity<String> response = restTemplate.postForEntity(approvalUrl,httpEntity,String.class);

        System.out.println("Approval rest template response : " + response.getBody());

    }
}
