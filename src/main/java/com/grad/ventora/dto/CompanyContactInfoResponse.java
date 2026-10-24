
package com.grad.ventora.dto;

import com.grad.ventora.entity.PreferredCommunicationChannel;

import java.time.LocalDateTime;

public record CompanyContactInfoResponse(
        Long id,
        Long companyId,
        String officialEmail,
        String secondaryEmail,
        String contactPerson,
        String contactTitle,
        PreferredCommunicationChannel preferredChannel,
        String phoneNumber,
        String faxNumber,
        String website,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
