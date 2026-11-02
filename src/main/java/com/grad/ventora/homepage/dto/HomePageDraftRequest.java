package com.grad.ventora.homepage.dto;
import com.grad.ventora.homepage.entity.HomePageLocale;
import jakarta.validation.constraints.NotNull;
public record HomePageDraftRequest(@NotNull HomePageLocale locale) {}
