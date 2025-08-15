package com.workflow.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class RiskAssessment implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {

        System.out.println("Final risk assessment started for " + execution.getVariable("userName"));
        System.out.println("Assessment is in process ...");
        System.out.println("Final assessment completed.");
        if((int)execution.getVariable("loanAmount") >  5000000){
            execution.setVariable("riskStatus",true);
            System.out.println("Risk found, loan amount is higher than basic limit.");
        }else{
            execution.setVariable("riskStatus",false);
            System.out.println("No risk found.");
        }

//        call rest api for the actual business logic for risk assessment
    }
}
