package com.workflow.dto;

import com.workflow.model.Compliance;
import com.workflow.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ComplianceRequestDTO {
    private Compliance compliance;
    private User user;
}
