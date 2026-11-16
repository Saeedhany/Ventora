package com.grad.ventora.integrations.dto;
import jakarta.validation.constraints.*;
import java.util.Set;
public record IntegrationRequest(
 @NotBlank @Size(max=120) String name,
 @NotBlank @Size(max=150) @Pattern(regexp="^[a-z0-9]+(?:-[a-z0-9]+)*$") String slug,
 @Size(max=10000) String description,
 @Size(max=500) String logoUrl,
 @Size(max=500) String websiteUrl,
 Integer displayOrder,
 Boolean active,
 Boolean isNew,
 Set<Long> categoryIds
) {}
