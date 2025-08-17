package com.workflow.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class RiskAssessment implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println();
        System.out.println("*********** Risk Assessment Started ****************");
        System.out.println("Risk assessment started for " + execution.getVariable("userName"));
        if ((int) execution.getVariable("loanAmount") > 5000000) {
            execution.setVariable("riskStatus", true);
            System.out.println("Risk found, loan amount is higher than basic limit.");
        } else {
            execution.setVariable("riskStatus", false);
            System.out.println("No risk found.");
        }

        System.out.println("*********** Risk Assessment Ended ****************");
        System.out.println();

//        call rest api for the actual business logic for risk assessment
    }
}
