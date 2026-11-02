package com.grad.ventora.homepage.dto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;
public record HomePageReorderRequest(@NotEmpty List<@NotNull Long> sectionIds) {}
