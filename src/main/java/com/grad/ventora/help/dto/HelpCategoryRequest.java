package com.grad.ventora.help.dto;
import jakarta.validation.constraints.*;
public record HelpCategoryRequest(
    @NotBlank @Size(max=100) String name,
    @NotBlank @Size(max=120) String slug,
    @Size(max=500) String description,
    @NotNull Integer displayOrder,
    Boolean active
) {}
