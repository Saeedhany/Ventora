package com.grad.ventora.pricing.service;

import com.grad.ventora.dto.PricingDraftResponse;
import com.grad.ventora.dto.PricingPlanRequest;
import com.grad.ventora.dto.PricingPlanResponse;

import java.util.List;

public interface IPricingService {

    List<PricingPlanResponse> getPublishedPlans();

    PricingDraftResponse createDraft();

    PricingPlanResponse addPlanToDraft(Long draftId, PricingPlanRequest request);

    PricingPlanResponse updatePlanInDraft(
            Long draftId,
            Long planId,
            PricingPlanRequest request
    );

    void deletePlanFromDraft(Long draftId, Long planId);

    void publishDraft(Long draftId);
}
