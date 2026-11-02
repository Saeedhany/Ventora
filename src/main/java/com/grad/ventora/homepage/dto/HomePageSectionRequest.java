package com.grad.ventora.homepage.dto;
import com.grad.ventora.homepage.entity.HomePageSectionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
public record HomePageSectionRequest(@NotNull HomePageSectionType sectionType, @Min(0) Integer displayOrder, Boolean visible) {}
