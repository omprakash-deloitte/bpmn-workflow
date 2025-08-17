package com.workflow.service;

import com.workflow.dto.ComplianceRequestDTO;
import com.workflow.model.Compliance;
import com.workflow.model.User;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class ComplianceCheckService implements JavaDelegate {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Map<String, String> complianceCheck = (Map<String, String>) execution.getVariable("complianceCheck");
        Compliance compliance = new Compliance();
        for (Map.Entry<String, String> entry : complianceCheck.entrySet()) {
            switch (entry.getKey()) {
                case "type":
                    compliance.setType(entry.getValue());
                    break;

                case "name":
                    compliance.setName(entry.getValue());
                    break;

                case "description":
                    compliance.setDescription(entry.getValue());
                    break;

                case "isRequired":
                    boolean isRequired = !entry.getValue().equalsIgnoreCase("no");
                    compliance.setIsRequired(isRequired);
                    break;
            }
        }

        User user = getUserWithData(execution);
        ComplianceRequestDTO complianceRequestDTO = new ComplianceRequestDTO();
        complianceRequestDTO.setCompliance(compliance);
        complianceRequestDTO.setUser(user);

        String complianceUrl = "http://localhost:8081/loan/check-compliance";

        HttpEntity<ComplianceRequestDTO> complianceHttpEntity = new HttpEntity<>(complianceRequestDTO);

        ResponseEntity<Boolean> response = restTemplate.postForEntity(complianceUrl, complianceHttpEntity, Boolean.class);
        boolean isComplianceCheckFailed = (boolean) execution.getVariable("isComplianceCheckFailed");

        if (!isComplianceCheckFailed) {
            execution.setVariable("isComplianceCheckFailed", !Boolean.TRUE.equals(response.getBody()));
        }

    }

    public User getUserWithData(DelegateExecution execution){
        User user = new User();
        user.setUserId((Integer) execution.getVariable("userId"));
        user.setUserName((String) execution.getVariable("userName"));
        user.setAge((Integer) execution.getVariable("age"));
        user.setGender((String) execution.getVariable("gender"));
        user.setPhoneNumber((String) execution.getVariable("phoneNumber"));
        user.setAddress((String) execution.getVariable("address"));
        user.setLoanAmount((Integer) execution.getVariable("loanAmount"));
        user.setCreditScore((Integer) execution.getVariable("creditScore"));

        return user;
    }
}
