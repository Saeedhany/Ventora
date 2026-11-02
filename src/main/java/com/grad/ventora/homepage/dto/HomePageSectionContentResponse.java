package com.grad.ventora.homepage.dto;

import com.grad.ventora.homepage.entity.HomePageSectionType;

/** Complete editable content for a single section. */
public record HomePageSectionContentResponse(Long sectionId, HomePageSectionType sectionType,
                                             HomePageSectionContentRequest content) {}
