package com.grad.ventora.help.dto;
import java.time.LocalDateTime;
public record HelpFaqResponse(Long id,String question,String answer,HelpCategoryResponse category,
    Integer displayOrder,String status,Boolean featured,LocalDateTime createdAt,LocalDateTime updatedAt) {}
