package com.grad.ventora.help.dto;
import jakarta.validation.constraints.*;
public record HelpFaqRequest(
    @NotBlank @Size(max=500) String question,
    @NotBlank String answer,
    @NotNull Long categoryId,
    @NotNull Integer displayOrder,
    Boolean featured
) {}
