package com.workflow.service;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class GenerateLoanDocument implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^ LOAN DOCUMENT GENERATION ^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("Loan Document generated for " + execution.getVariable("userName") + " with user Id " + execution.getVariable("userId"));
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^ LOAN DOCUMENT GENERATION ^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println();

//        call rest api for actual implementation
    }
}
