
package com.grad.ventora.dto;

import com.grad.ventora.entity.CompanyProfileStatus;
import java.time.LocalDateTime;

public record CompanyProfileResponse(
        Long id,
        String companyName,
        String legalName,
        String industry,
        String companySize,
        Integer foundedYear,
        String website,
        String headquartersCountry,
        String headquartersCity,
        String businessDescription,
        String companyLogoUrl,
        String legalEntityType,
        String countryOfIncorporation,
        String mainBusinessDomain,
        String primaryBusinessModel,
        CompanyProfileStatus profileStatus,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
