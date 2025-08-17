package com.workflow.service;

import com.workflow.dto.LoanRequestDTO;
import com.workflow.model.User;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EmailNotificationService implements JavaDelegate {

    @Autowired
    private RestTemplate restTemplate;

    private final String approvalUrl = "http://localhost:8081/loan/send-notification";

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

        LoanRequestDTO loanRequestDTO = new LoanRequestDTO();
        loanRequestDTO.setIsEligibleForLoan(true);
        loanRequestDTO.setUser(user);
        loanRequestDTO.setNotificationType((String) execution.getVariable("notificationType"));

        HttpEntity<LoanRequestDTO> httpEntity = new HttpEntity<>(loanRequestDTO);
        ResponseEntity<String> response = restTemplate.postForEntity(approvalUrl, httpEntity, String.class);
        System.out.println();
        System.out.println("___________________________________________________");
        System.out.println("Notification REST api response : " + response.getBody());
        System.out.println("___________________________________________________");
        System.out.println();
    }
}
