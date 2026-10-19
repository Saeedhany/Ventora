
package com.grad.ventora.repository;

import com.grad.ventora.entity.PlanFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanFeatureRepository
        extends JpaRepository<PlanFeature, Long> {

    List<PlanFeature> findByPricingPlanIdOrderByDisplayOrderAsc(
            Long pricingPlanId
    );
}
