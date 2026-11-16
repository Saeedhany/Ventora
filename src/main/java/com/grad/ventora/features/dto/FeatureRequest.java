package com.grad.ventora.features.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

public record FeatureRequest(
        @NotBlank @Size(max = 120) String name,
        @NotBlank @Size(max = 150)
        @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "must contain lowercase letters, numbers and hyphens only")
        String slug,
        @NotBlank @Size(max = 150) String sectionLabel,
        @NotBlank @Size(max = 250) String title,
        @NotBlank @Size(max = 10000) String description,
        @Size(max = 500) String imageUrl,
        @Size(max = 100) String ctaLabel,
        @Size(max = 500) String ctaUrl,
        Integer displayOrder,
        Boolean active,
        @Valid List<FeaturePointRequest> points,
        @Valid List<FeatureSectionRequest> sections
) {}
