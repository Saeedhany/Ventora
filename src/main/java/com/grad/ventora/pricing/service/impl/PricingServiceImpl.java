
package com.grad.ventora.pricing.service.impl;

import com.grad.ventora.dto.*;
import com.grad.ventora.entity.PricingContent;
import com.grad.ventora.entity.PricingContentStatus;
import com.grad.ventora.entity.PricingPlan;
import com.grad.ventora.entity.PlanFeature;
import com.grad.ventora.pricing.service.IPricingService;
import com.grad.ventora.repository.PricingContentRepository;
import com.grad.ventora.repository.PricingPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PricingServiceImpl implements IPricingService {

    private final PricingContentRepository pricingContentRepository;
    private final PricingPlanRepository pricingPlanRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PricingPlanResponse> getPublishedPlans() {
        PricingContent content = pricingContentRepository
                .findFirstByStatusOrderByCreatedAtDesc(
                        PricingContentStatus.PUBLISHED
                )
                .orElseThrow(() -> new IllegalStateException(
                        "No published pricing content is available"
                ));

        return content.getPlans()
                .stream()
                .filter(PricingPlan::isActive)
                .map(this::toPricingPlanResponse)
                .toList();
    }


    @Override
    @Transactional
    public PricingDraftResponse createDraft() {
        PricingContent draft = new PricingContent();
        draft.setStatus(PricingContentStatus.DRAFT);

        PricingContent publishedContent = pricingContentRepository
                .findFirstByStatusOrderByCreatedAtDesc(
                        PricingContentStatus.PUBLISHED
                )
                .orElse(null);

        if (publishedContent != null) {
            for (PricingPlan publishedPlan : publishedContent.getPlans()) {
                PricingPlan copiedPlan = new PricingPlan();

                copiedPlan.setName(publishedPlan.getName());
                copiedPlan.setDescription(publishedPlan.getDescription());
                copiedPlan.setMonthlyPrice(publishedPlan.getMonthlyPrice());
                copiedPlan.setAnnualPrice(publishedPlan.getAnnualPrice());
                copiedPlan.setCurrency(publishedPlan.getCurrency());
                copiedPlan.setActive(publishedPlan.isActive());
                copiedPlan.setFeatured(publishedPlan.isFeatured());
                copiedPlan.setDisplayOrder(publishedPlan.getDisplayOrder());
                copiedPlan.setPricingContent(draft);

                for (PlanFeature publishedFeature : publishedPlan.getFeatures()) {
                    PlanFeature copiedFeature = new PlanFeature();

                    copiedFeature.setName(publishedFeature.getName());
                    copiedFeature.setValue(publishedFeature.getValue());
                    copiedFeature.setDisplayOrder(
                            publishedFeature.getDisplayOrder()
                    );
                    copiedFeature.setPricingPlan(copiedPlan);

                    copiedPlan.getFeatures().add(copiedFeature);
                }

                draft.getPlans().add(copiedPlan);
            }
        }

        PricingContent savedDraft = pricingContentRepository.save(draft);

        return new PricingDraftResponse(
                savedDraft.getId(),
                savedDraft.getStatus().name()
        );
    }


    @Override
    @Transactional
    public PricingPlanResponse addPlanToDraft(
            Long draftId,
            PricingPlanRequest request
    ) {
        PricingContent draft = getDraftOrThrow(draftId);

        if (pricingPlanRepository
                .existsByPricingContentIdAndNameIgnoreCase(
                        draftId, request.name()
                )) {
            throw new IllegalArgumentException(
                    "A plan with this name already exists in the draft"
            );
        }

        PricingPlan plan = new PricingPlan();
        plan.setPricingContent(draft);

        updatePlanFields(plan, request);
        addFeatures(plan, request.features());

        draft.getPlans().add(plan);

        PricingPlan saved = pricingPlanRepository.save(plan);

        return toPricingPlanResponse(saved);
    }

    @Override
    @Transactional
    public PricingPlanResponse updatePlanInDraft(
            Long draftId,
            Long planId,
            PricingPlanRequest request
    ) {
        PricingContent draft = getDraftOrThrow(draftId);

        PricingPlan plan = pricingPlanRepository.findById(planId)
                .filter(p -> p.getPricingContent().getId().equals(draftId))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Plan not found in this draft"
                ));

        if (pricingPlanRepository
                .existsByPricingContentIdAndNameIgnoreCaseAndIdNot(
                        draftId, request.name(), planId
                )) {
            throw new IllegalArgumentException(
                    "A plan with this name already exists in the draft"
            );
        }

        updatePlanFields(plan, request);

        plan.getFeatures().clear();
        addFeatures(plan, request.features());

        return toPricingPlanResponse(plan);
    }

    @Override
    @Transactional
    public void deletePlanFromDraft(Long draftId, Long planId) {
        getDraftOrThrow(draftId);

        PricingPlan plan = pricingPlanRepository.findById(planId)
                .filter(p -> p.getPricingContent().getId().equals(draftId))
                .orElseThrow(() -> new IllegalArgumentException(
                        "Plan not found in this draft"
                ));

        pricingPlanRepository.delete(plan);
    }


    @Override
    @Transactional
    public void publishDraft(Long draftId) {
        PricingContent draft = getDraftOrThrow(draftId);

        if (draft.getPlans().isEmpty()) {
            throw new IllegalStateException(
                    "Cannot publish a draft without pricing plans"
            );
        }

        long activePlansCount = draft.getPlans()
                .stream()
                .filter(PricingPlan::isActive)
                .count();

        if (activePlansCount == 0) {
            throw new IllegalStateException(
                    "Cannot publish a draft without active plans"
            );
        }

        boolean hasMultipleFeaturedPlans = draft.getPlans()
                .stream()
                .filter(PricingPlan::isActive)
                .filter(PricingPlan::isFeatured)
                .count() > 1;

        if (hasMultipleFeaturedPlans) {
            throw new IllegalStateException(
                    "Only one active plan can be featured"
            );
        }

        List<PricingContent> publishedContents =
                pricingContentRepository.findByStatus(
                        PricingContentStatus.PUBLISHED
                );

        for (PricingContent content : publishedContents) {
            content.setStatus(PricingContentStatus.ARCHIVED);
        }

        draft.setStatus(PricingContentStatus.PUBLISHED);
        draft.setPublishedAt(LocalDateTime.now());

        pricingContentRepository.saveAll(publishedContents);
        pricingContentRepository.save(draft);
    }


    private PricingContent getDraftOrThrow(Long draftId) {
        PricingContent content = pricingContentRepository.findById(draftId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Pricing draft not found"
                ));

        if (content.getStatus() != PricingContentStatus.DRAFT) {
            throw new IllegalStateException(
                    "Only draft content can be modified or published"
            );
        }

        return content;
    }

    private void updatePlanFields(
            PricingPlan plan,
            PricingPlanRequest request
    ) {
        plan.setName(request.name());
        plan.setDescription(request.description());
        plan.setMonthlyPrice(request.monthlyPrice());
        plan.setAnnualPrice(request.annualPrice());
        plan.setCurrency(request.currency().toUpperCase());
        plan.setActive(request.active());
        plan.setFeatured(request.featured());
        plan.setDisplayOrder(request.displayOrder());
    }

    private void addFeatures(
            PricingPlan plan,
            List<PlanFeatureRequest> requests
    ) {
        for (PlanFeatureRequest request : requests) {
            PlanFeature feature = new PlanFeature();
            feature.setName(request.name());
            feature.setValue(request.value());
            feature.setDisplayOrder(request.displayOrder());
            feature.setPricingPlan(plan);

            plan.getFeatures().add(feature);
        }
    }

    private PricingPlanResponse toPricingPlanResponse(
            PricingPlan plan
    ) {
        List<PlanFeatureResponse> features = plan.getFeatures()
                .stream()
                .map(feature -> new PlanFeatureResponse(
                        feature.getId(),
                        feature.getName(),
                        feature.getValue(),
                        feature.getDisplayOrder()
                ))
                .toList();

        return new PricingPlanResponse(
                plan.getId(),
                plan.getName(),
                plan.getDescription(),
                plan.getMonthlyPrice(),
                plan.getAnnualPrice(),
                plan.getCurrency(),
                plan.isFeatured(),
                plan.getDisplayOrder(),
                features
        );
    }
}
