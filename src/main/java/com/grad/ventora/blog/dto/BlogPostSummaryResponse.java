package com.grad.ventora.blog.dto;
import java.time.LocalDateTime;
public record BlogPostSummaryResponse(Long id,String title,String slug,String excerpt,String coverImageUrl,String authorName,String authorImageUrl,String category,Integer readingTime,LocalDateTime publishedAt,Boolean featured) {}
