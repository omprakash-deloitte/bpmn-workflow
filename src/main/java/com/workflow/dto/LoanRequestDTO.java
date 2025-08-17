package com.workflow.dto;

import com.workflow.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequestDTO {
    private User user;
    private Boolean isEligibleForLoan;
    private Integer loanAmount;
    private String notificationType;
}

