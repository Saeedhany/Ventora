package com.grad.ventora.features.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
public record FeaturePointRequest(@NotBlank @Size(max = 300) String content, Integer displayOrder) {}
