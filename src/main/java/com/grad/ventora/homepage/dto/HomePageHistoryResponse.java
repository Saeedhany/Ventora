package com.grad.ventora.homepage.dto;
import com.grad.ventora.homepage.entity.HomePageHistoryAction;
import com.grad.ventora.homepage.entity.HomePageLocale;
import java.time.LocalDateTime;
public record HomePageHistoryResponse(Long id, Long contentId, Integer version, HomePageLocale locale, HomePageHistoryAction action, String releaseNote, String actor, LocalDateTime createdAt) {}
