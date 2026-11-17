package com.grad.ventora.documentation.dto;
import jakarta.validation.constraints.*;
public record DocumentationCategoryRequest(@NotBlank @Size(max=120) String name,@NotBlank @Size(max=140) String slug,@Size(max=500) String description,@NotNull @Min(0) Integer displayOrder,Boolean active) {}
