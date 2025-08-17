package com.workflow.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkflowService implements JavaDelegate {

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

        List<Map<String, String>> complianceCheckList = getMaps();

        execution.setVariable("complianceCheckList", complianceCheckList);
    }

    private static List<Map<String, String>> getMaps() {
        List<Map<String, String>> complianceCheckList = new ArrayList<>();

        Map<String, String> compliance1 = new HashMap<>();
        compliance1.put("type", "kyc_check");
        compliance1.put("name", "KYC Verification");
        compliance1.put("description", "Verify identity of applicant per Know Your Customer norms");
        compliance1.put("isRequired", "yes");

        Map<String, String> compliance2 = new HashMap<>();
        compliance2.put("type", "fraud_check");
        compliance2.put("name", "Blacklist / Fraud Check");
        compliance2.put("description", "Check against fraud or defaulter databases");
        compliance2.put("isRequired", "yes");

        Map<String, String> compliance3 = new HashMap<>();
        compliance3.put("type", "legal_check");
        compliance3.put("name", "Age & Legal Eligibility");
        compliance3.put("description", "Ensure applicant is of legal age and meets minimum eligibility criteria");
        compliance3.put("isRequired", "yes");

        complianceCheckList.add(compliance1);
        complianceCheckList.add(compliance2);
        complianceCheckList.add(compliance3);
        return complianceCheckList;
    }
}
