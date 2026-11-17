package com.grad.ventora.help.dto;
public record HelpCategoryResponse(Long id,String name,String slug,String description,
    Integer displayOrder,Boolean active,long faqCount) {}
