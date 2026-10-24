
package com.grad.ventora.dto;

import com.grad.ventora.entity.ManagementMemberType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ManagementTeamMemberResponse(
        Long id,
        Long companyId,
        ManagementMemberType memberType,
        String name,
        String department,
        String position,
        String responsibilities,
        BigDecimal ownershipShare,
        String operationalInvolvement,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
