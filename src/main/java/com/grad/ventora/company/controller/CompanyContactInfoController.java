
package com.grad.ventora.company.controller;

import com.grad.ventora.company.service.ICompanyContactInfoService;
import com.grad.ventora.dto.CompanyContactInfoRequest;
import com.grad.ventora.dto.CompanyContactInfoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/companies/{companyId}/contact-info")
@RequiredArgsConstructor
public class CompanyContactInfoController {

    private final ICompanyContactInfoService contactInfoService;

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyContactInfoResponse createContactInfo(
            @PathVariable Long companyId,
            @Valid @RequestBody CompanyContactInfoRequest request
    ) {
        return contactInfoService.saveContactInfo(companyId, request);
    }

    @GetMapping("/admin")
    public CompanyContactInfoResponse getContactInfo(
            @PathVariable Long companyId
    ) {
        return contactInfoService.getContactInfo(companyId);
    }

    @PutMapping("/admin")
    public CompanyContactInfoResponse updateContactInfo(
            @PathVariable Long companyId,
            @Valid @RequestBody CompanyContactInfoRequest request
    ) {
        return contactInfoService.updateContactInfo(companyId, request);
    }
}
