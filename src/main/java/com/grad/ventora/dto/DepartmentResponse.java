
package com.grad.ventora.dto;

import java.time.LocalDateTime;

public record DepartmentResponse(
        Long id,
        Long companyId,
        Long businessUnitId,
        String name,
        String manager,
        Integer teamCount,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
