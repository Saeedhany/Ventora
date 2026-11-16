package com.grad.ventora.integrations.category.dto;
import jakarta.validation.constraints.*;
public record CategoryRequest(@NotBlank @Size(max=100) String name,
 @NotBlank @Size(max=120) @Pattern(regexp="^[a-z0-9]+(?:-[a-z0-9]+)*$") String slug,
 @Size(max=5000) String description, @Size(max=500) String iconUrl,
 Integer displayOrder, Boolean active) {}
