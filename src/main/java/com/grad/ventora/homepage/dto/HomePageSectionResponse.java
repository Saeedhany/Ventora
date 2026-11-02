package com.grad.ventora.homepage.dto;
import com.grad.ventora.homepage.entity.HomePageSectionType;
public record HomePageSectionResponse(Long id, HomePageSectionType sectionType, Integer displayOrder, boolean visible, HomePageSectionContentRequest content) {}
