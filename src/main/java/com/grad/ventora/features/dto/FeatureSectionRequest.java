package com.grad.ventora.features.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Map;

public record FeatureSectionRequest(
        @NotBlank @Size(max = 60) String type,
        @Size(max = 200) String title,
        @Size(max = 250) String subtitle,
        @Size(max = 10000) String description,
        @Size(max = 500) String imageUrl,
        @Size(max = 120) String buttonLabel,
        @Size(max = 500) String buttonUrl,
        Map<String, Object> content,
        Integer displayOrder,
        Boolean active
) {}
