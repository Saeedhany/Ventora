package com.grad.ventora.documentation.dto;
import java.time.LocalDateTime;
public record DocumentationVersionResponse(Long id,String name,String slug,String description,Boolean isLatest,Boolean active,LocalDateTime createdAt,LocalDateTime updatedAt) {}
