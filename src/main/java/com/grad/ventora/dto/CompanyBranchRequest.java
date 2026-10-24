
package com.grad.ventora.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CompanyBranchRequest(

        @NotBlank
        @Size(max = 150)
        String name,

        boolean headquarters,

        @NotBlank
        @Size(max = 255)
        String addressLine,

        @Size(max = 100)
        String city,

        @Size(max = 100)
        String stateOrRegion,

        @Size(max = 30)
        String postalCode,

        @Size(max = 100)
        String country,

        @Size(max = 150)
        String contactName,

        @Size(max = 150)
        String contactTitle,

        @Email
        @Size(max = 255)
        String email,

        @Size(max = 30)
        String phoneNumber
) {
}
