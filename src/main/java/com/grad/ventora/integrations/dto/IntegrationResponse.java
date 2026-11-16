package com.grad.ventora.integrations.dto;
import java.time.LocalDateTime;
import java.util.List;
public record IntegrationResponse(Long id, String name, String slug, String description, String logoUrl,
 String websiteUrl, Integer displayOrder, boolean active, boolean isNew, List<CategorySummary> categories, LocalDateTime createdAt, LocalDateTime updatedAt) {
 public record CategorySummary(Long id, String name, String slug) {}
}
