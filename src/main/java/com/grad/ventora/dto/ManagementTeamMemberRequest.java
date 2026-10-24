
package com.grad.ventora.dto;

import com.grad.ventora.entity.ManagementMemberType;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ManagementTeamMemberRequest(

        @NotNull
        ManagementMemberType memberType,

        @NotBlank
        @Size(max = 150)
        String name,

        @Size(max = 150)
        String department,

        @Size(max = 150)
        String position,

        @Size(max = 1000)
        String responsibilities,

        @DecimalMin("0.00")
        @DecimalMax("100.00")
        BigDecimal ownershipShare,

        @Size(max = 50)
        String operationalInvolvement,

        Boolean active
) {}
