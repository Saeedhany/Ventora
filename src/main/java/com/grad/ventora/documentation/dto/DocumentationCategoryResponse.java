package com.grad.ventora.documentation.dto;
public record DocumentationCategoryResponse(Long id,String name,String slug,String description,Integer displayOrder,Boolean active,long articleCount) {}
