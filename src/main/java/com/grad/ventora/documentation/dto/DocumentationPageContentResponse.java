package com.grad.ventora.documentation.dto;
import java.time.LocalDateTime;
public record DocumentationPageContentResponse(Long id,String heroTitle,String heroDescription,String searchPlaceholder,String needHelpTitle,String needHelpDescription,String getStartedLabel,String getStartedUrl,LocalDateTime createdAt,LocalDateTime updatedAt) {}
