
package com.grad.ventora.dto;

public record PlanFeatureResponse(
        Long id,
        String name,
        String value,
        Integer displayOrder
) {
}
