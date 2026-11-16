package com.grad.ventora.blog.dto;
import jakarta.validation.constraints.*; import java.util.*;
public record BlogPostRequest(@NotBlank @Size(max=250) String title,@NotBlank @Size(max=280) String slug,String excerpt,String coverImageUrl,@NotEmpty List<Map<String,Object>> content,@NotBlank String authorName,String authorImageUrl,@NotNull Long categoryId,@NotNull @Min(1) Integer readingTime,Boolean featured) {}
