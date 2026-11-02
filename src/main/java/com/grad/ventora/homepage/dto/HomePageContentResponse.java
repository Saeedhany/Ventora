package com.grad.ventora.homepage.dto;
import com.grad.ventora.homepage.entity.HomePageContentStatus;
import com.grad.ventora.homepage.entity.HomePageLocale;
import java.time.LocalDateTime;
import java.util.List;
public record HomePageContentResponse(Long id, Integer version, HomePageLocale locale, HomePageContentStatus status, String seoTitle, String seoDescription, LocalDateTime createdAt, LocalDateTime publishedAt, List<HomePageSectionResponse> sections) {}
