
package com.grad.ventora.company.controller;

import com.grad.ventora.company.service.ICompanyService;
import com.grad.ventora.dto.CompanyProfileRequest;
import com.grad.ventora.dto.CompanyProfileResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final ICompanyService companyService;

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyProfileResponse createDraft(
            @Valid @RequestBody CompanyProfileRequest request
    ) {
        return companyService.createDraft(request);
    }

    @GetMapping("/{companyId}")
    public CompanyProfileResponse getCompanyById(
            @PathVariable Long companyId
    ) {
        return companyService.getCompanyById(companyId);
    }

    @PutMapping("/{companyId}/admin")
    public CompanyProfileResponse updateDraft(
            @PathVariable Long companyId,
            @Valid @RequestBody CompanyProfileRequest request
    ) {
        return companyService.updateDraft(companyId, request);
    }

    @GetMapping("/admin")
    public List<CompanyProfileResponse> getAllCompanies() {
        return companyService.getAllCompanies();
    }
}
