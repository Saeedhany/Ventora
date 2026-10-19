
package com.grad.ventora.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;

public record PricingPlanRequest(
        @NotBlank
        @Size(max = 50)
        String name,

        @Size(max = 1000)
        String description,

        @NotNull
        @DecimalMin("0.0")
        BigDecimal monthlyPrice,

        @NotNull
        @DecimalMin("0.0")
        BigDecimal annualPrice,

        @NotBlank
        @Size(min = 3, max = 3)
        String currency,

        boolean active,
        boolean featured,

        @NotNull
        @Min(1)
        Integer displayOrder,

        @NotEmpty
        @NotNull
        @Valid
        List<PlanFeatureRequest> features
) {}
