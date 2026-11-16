package com.grad.ventora.integrations.pagecontent.dto;

import jakarta.validation.constraints.Size;
import java.util.List;

public record IntegrationPageContentRequest(
        @Size(max = 250) String heroTitle,
        String heroDescription,
        @Size(max = 500) String heroImageUrl,
        @Size(max = 120) String heroButtonLabel,
        @Size(max = 500) String heroButtonUrl,

        @Size(max = 250) String popularTitle,
        String popularDescription,

        @Size(max = 250) String easySetupTitle,
        String easySetupDescription,
        List<@Size(max = 250) String> easySetupPoints,
        @Size(max = 120) String easySetupButtonLabel,
        @Size(max = 500) String easySetupButtonUrl,

        @Size(max = 250) String categoriesTitle,
        String categoriesDescription,

        @Size(max = 250) String bottomCtaTitle,
        String bottomCtaDescription,
        @Size(max = 120) String bottomCtaButtonLabel,
        @Size(max = 500) String bottomCtaButtonUrl
) {}
