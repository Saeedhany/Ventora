
package com.grad.ventora.repository;

import com.grad.ventora.entity.PricingContent;
import com.grad.ventora.entity.PricingContentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PricingContentRepository
        extends JpaRepository<PricingContent, Long> {


    Optional<PricingContent> findFirstByStatusOrderByCreatedAtDesc(
            PricingContentStatus status
    );

    List<PricingContent> findByStatus(PricingContentStatus status);

}
