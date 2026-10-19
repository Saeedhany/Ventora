
package com.grad.ventora.pricing.controller;

import com.grad.ventora.dto.PricingDraftResponse;
import com.grad.ventora.dto.PricingPlanRequest;
import com.grad.ventora.dto.PricingPlanResponse;
import com.grad.ventora.pricing.service.IPricingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pricing")
public class PricingController {

    private final IPricingService pricingService;

    @GetMapping("/plans")
    public ResponseEntity<List<PricingPlanResponse>> getPublishedPlans() {
        return ResponseEntity.ok(pricingService.getPublishedPlans());
    }

    @PostMapping("/drafts/admin")
    public ResponseEntity<PricingDraftResponse> createDraft() {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pricingService.createDraft());
    }

    @PostMapping("/drafts/{draftId}/plans/admin")
    public ResponseEntity<PricingPlanResponse> addPlanToDraft(
            @PathVariable Long draftId,
            @Valid @RequestBody PricingPlanRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(pricingService.addPlanToDraft(draftId, request));
    }

    @PutMapping("/drafts/{draftId}/plans/{planId}/admin")
    public ResponseEntity<PricingPlanResponse> updatePlanInDraft(
            @PathVariable Long draftId,
            @PathVariable Long planId,
            @Valid @RequestBody PricingPlanRequest request
    ) {
        return ResponseEntity.ok(
                pricingService.updatePlanInDraft(draftId, planId, request)
        );
    }

    @DeleteMapping("/drafts/{draftId}/plans/{planId}/admin")
    public ResponseEntity<Void> deletePlanFromDraft(
            @PathVariable Long draftId,
            @PathVariable Long planId
    ) {
        pricingService.deletePlanFromDraft(draftId, planId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/drafts/{draftId}/publish/admin")
    public ResponseEntity<Void> publishDraft(
            @PathVariable Long draftId
    ) {
        pricingService.publishDraft(draftId);
        return ResponseEntity.noContent().build();
    }
}