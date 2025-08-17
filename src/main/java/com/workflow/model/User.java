package com.workflow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer userId;
    private String userName;
    private Integer age;
    private String phoneNumber;
    private String address;
    private Integer creditScore = 0;
    private String gender;
    private Integer loanAmount;
}
