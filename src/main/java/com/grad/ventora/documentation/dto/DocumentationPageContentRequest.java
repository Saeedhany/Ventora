package com.grad.ventora.documentation.dto;
import jakarta.validation.constraints.Size;
public record DocumentationPageContentRequest(@Size(max=300) String heroTitle,@Size(max=1000) String heroDescription,@Size(max=200) String searchPlaceholder,@Size(max=300) String needHelpTitle,@Size(max=1000) String needHelpDescription,@Size(max=100) String getStartedLabel,@Size(max=500) String getStartedUrl) {}
