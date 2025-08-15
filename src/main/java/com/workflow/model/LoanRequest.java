package com.workflow.model;

public class LoanRequest {
    private User user;

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

    private Boolean isEligibleForLoan;
}
