package com.grad.ventora.blog.dto;
public record BlogCategoryResponse(Long id,String name,String slug,String description,Integer displayOrder,Boolean active,long postCount) {}
