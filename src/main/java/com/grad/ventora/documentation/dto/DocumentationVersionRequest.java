package com.grad.ventora.documentation.dto;
import jakarta.validation.constraints.*;
public record DocumentationVersionRequest(@NotBlank @Size(max=100) String name,@NotBlank @Size(max=120) String slug,@Size(max=500) String description,Boolean active) {}
