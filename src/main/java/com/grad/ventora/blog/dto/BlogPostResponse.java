package com.grad.ventora.blog.dto;
import java.time.LocalDateTime; import java.util.*;
public record BlogPostResponse(Long id,String title,String slug,String excerpt,String coverImageUrl,List<Map<String,Object>> content,String authorName,String authorImageUrl,BlogCategoryResponse category,Integer readingTime,String status,Boolean featured,LocalDateTime publishedAt,LocalDateTime createdAt,LocalDateTime updatedAt) {}
