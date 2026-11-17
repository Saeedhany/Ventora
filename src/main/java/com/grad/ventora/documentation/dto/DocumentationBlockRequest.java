package com.grad.ventora.documentation.dto;
import com.grad.ventora.documentation.entity.DocumentationBlockType; import jakarta.validation.constraints.*; import java.util.Map;
public record DocumentationBlockRequest(@NotNull DocumentationBlockType type,@NotNull Map<String,Object> content,@NotNull @Min(0) Integer displayOrder) {}
