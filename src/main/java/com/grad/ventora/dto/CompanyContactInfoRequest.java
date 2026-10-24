
package com.grad.ventora.dto;

import com.grad.ventora.entity.PreferredCommunicationChannel;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CompanyContactInfoRequest(

        @NotBlank
        @Email
        @Size(max = 255)
        String officialEmail,

        @Email
        @Size(max = 255)
        String secondaryEmail,

        @Size(max = 150)
        String contactPerson,

        @Size(max = 150)
        String contactTitle,

        PreferredCommunicationChannel preferredChannel,

        @NotBlank
        @Size(max = 30)
        String phoneNumber,

        @Size(max = 30)
        String faxNumber
) {
}
