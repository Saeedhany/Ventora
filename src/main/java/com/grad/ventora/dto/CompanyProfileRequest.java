
package com.grad.ventora.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CompanyProfileRequest(

        @Size(max = 150)
        String companyName,

        @Size(max = 200)
        String legalName,

        @Size(max = 100)
        String industry,

        @Size(max = 50)
        String companySize,

        @Min(1800)
        @Max(2100)
        Integer foundedYear,

        @Size(max = 255)
        @Pattern(
                regexp = "^(https?://\\S+)?$",
                message = "Website must be a valid HTTP or HTTPS URL"
        )
        String website,

        @Size(max = 100)
        String headquartersCountry,

        @Size(max = 100)
        String headquartersCity,

        @Size(max = 5000)
        String businessDescription,

        @Size(max = 500)
        String companyLogoUrl,

        @Size(max = 100)
        String legalEntityType,

        @Size(max = 100)
        String countryOfIncorporation,

        @Size(max = 150)
        String mainBusinessDomain,

        @Size(max = 150)
        String primaryBusinessModel

) {
}
