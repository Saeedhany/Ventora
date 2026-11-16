package com.grad.ventora.features.dto;
import java.time.LocalDateTime;
import java.util.List;
public record FeatureResponse(
        Long id, String name, String slug, String sectionLabel, String title,
        String description, String imageUrl, String ctaLabel, String ctaUrl,
        Integer displayOrder, boolean active, List<FeaturePointResponse> points,
        LocalDateTime createdAt, LocalDateTime updatedAt, List<FeatureSectionResponse> sections
) {
    public record FeaturePointResponse(Long id, String content, Integer displayOrder) {}
    public record FeatureSectionResponse(
            Long id, String type, String title, String subtitle, String description,
            String imageUrl, String buttonLabel, String buttonUrl,
            java.util.Map<String, Object> content, Integer displayOrder, boolean active
    ) {}
}
