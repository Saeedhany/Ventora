
package com.grad.ventora.dto;

import java.time.LocalDateTime;

public record BusinessUnitResponse(
        Long id,
        Long companyId,
        String name,
        Long parentUnitId,
        String location,
        String headManager,
        String scope,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
