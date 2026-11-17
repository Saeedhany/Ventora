package com.grad.ventora.documentation.dto;
import com.grad.ventora.documentation.entity.DocumentationBlockType; import java.util.Map;
public record DocumentationBlockResponse(Long id,DocumentationBlockType type,Map<String,Object> content,Integer displayOrder) {}
