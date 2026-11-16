package com.grad.ventora.features.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
public record FeatureReorderRequest(@NotEmpty List<@NotNull Long> featureIds) {}
