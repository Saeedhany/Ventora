
package com.grad.ventora.dto;

import java.time.LocalDateTime;

public record CompanyBranchResponse(
        Long id,
        Long companyId,
        String name,
        boolean headquarters,
        String addressLine,
        String city,
        String stateOrRegion,
        String postalCode,
        String country,
        String contactName,
        String contactTitle,
        String email,
        String phoneNumber,
        boolean active,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
