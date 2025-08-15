package com.workflow.model;


public class LoanRequest {
    private User user;
    private Boolean isEligibleForLoan;
    private Integer loanAmount;
    private String notificationType;

    public String getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(String notificationType) {
        this.notificationType = notificationType;
    }

    public Integer getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(Integer loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Boolean getEligibleForLoan() {
        return isEligibleForLoan;
    }

    public void setEligibleForLoan(Boolean eligibleForLoan) {
        isEligibleForLoan = eligibleForLoan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

