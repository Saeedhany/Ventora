
package com.grad.ventora.company.controller;

import com.grad.ventora.company.service.IBusinessSettingsService;
import com.grad.ventora.dto.BusinessSettingsRequest;
import com.grad.ventora.dto.BusinessSettingsResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/companies/{companyId}/business-settings")
@RequiredArgsConstructor
public class BusinessSettingsController {

    private final IBusinessSettingsService businessSettingsService;

    @GetMapping
    public BusinessSettingsResponse getSettings(
            @PathVariable Long companyId
    ) {
        return businessSettingsService.getSettings(companyId);
    }

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public BusinessSettingsResponse saveSettings(
            @PathVariable Long companyId,
            @Valid @RequestBody BusinessSettingsRequest request
    ) {
        return businessSettingsService.saveSettings(companyId, request);
    }

    @PutMapping("/admin")
    public BusinessSettingsResponse updateSettings(
            @PathVariable Long companyId,
            @Valid @RequestBody BusinessSettingsRequest request
    ) {
        return businessSettingsService.updateSettings(companyId, request);
    }
}
