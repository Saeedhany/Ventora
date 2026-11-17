package com.grad.ventora.documentation.dto;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import java.util.List;
public record DocumentationArticleRequest(@NotBlank @Size(max=250) String title,@NotBlank @Size(max=180) String slug,@Size(max=1000) String description,@NotNull Long categoryId,@NotNull Long versionId,@NotNull @Min(0) Integer displayOrder,@Valid @NotNull List<DocumentationBlockRequest> contentBlocks,List<Long> relatedArticleIds) {}
