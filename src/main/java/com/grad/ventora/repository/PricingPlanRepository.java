
package com.grad.ventora.repository;

import com.grad.ventora.entity.PricingPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PricingPlanRepository
        extends JpaRepository<PricingPlan, Long> {

    List<PricingPlan> findByPricingContentIdOrderByDisplayOrderAsc(
            Long pricingContentId
    );


    boolean existsByPricingContentIdAndNameIgnoreCaseAndIdNot(
            Long pricingContentId,
            String name,
            Long id
    );

    boolean existsByPricingContentIdAndNameIgnoreCase(
            Long pricingContentId,
            String name
    );

}
