package com.grad.ventora.integrations.category.dto;
import java.util.List;
public record CategoryResponse(Long id, String name, String slug, String description, String iconUrl,
 Integer displayOrder, boolean active, List<IntegrationSummary> integrations) {
 public record IntegrationSummary(Long id, String name, String slug, String logoUrl) {}
}
