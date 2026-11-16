package com.grad.ventora.integrations.pagecontent.dto;

import java.time.LocalDateTime;
import java.util.List;

public record IntegrationPageContentResponse(
        Long id,
        String heroTitle,
        String heroDescription,
        String heroImageUrl,
        String heroButtonLabel,
        String heroButtonUrl,
        String popularTitle,
        String popularDescription,
        String easySetupTitle,
        String easySetupDescription,
        List<String> easySetupPoints,
        String easySetupButtonLabel,
        String easySetupButtonUrl,
        String categoriesTitle,
        String categoriesDescription,
        String bottomCtaTitle,
        String bottomCtaDescription,
        String bottomCtaButtonLabel,
        String bottomCtaButtonUrl,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
