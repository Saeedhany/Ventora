package com.grad.ventora.blog.dto;
import java.time.LocalDateTime;
public record BlogPageContentResponse(Long id,String heroTitle,String heroDescription,String blogTitle,String blogDescription,String newsletterTitle,String newsletterDescription,String bottomCtaTitle,String bottomCtaDescription,LocalDateTime createdAt,LocalDateTime updatedAt) {}
