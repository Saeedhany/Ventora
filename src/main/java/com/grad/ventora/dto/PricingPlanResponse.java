
package com.grad.ventora.dto;

import java.math.BigDecimal;
import java.util.List;

public record PricingPlanResponse(
        Long id,
        String name,
        String description,
        BigDecimal monthlyPrice,
        BigDecimal annualPrice,
        String currency,
        boolean featured,
        Integer displayOrder,
        List<PlanFeatureResponse> features
) {
}
