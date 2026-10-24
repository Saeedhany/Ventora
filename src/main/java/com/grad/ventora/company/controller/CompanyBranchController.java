
package com.grad.ventora.company.controller;

import com.grad.ventora.company.service.ICompanyBranchService;
import com.grad.ventora.dto.CompanyBranchRequest;
import com.grad.ventora.dto.CompanyBranchResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/companies/{companyId}/branches")
@RequiredArgsConstructor
public class CompanyBranchController {

    private final ICompanyBranchService branchService;

    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyBranchResponse createBranch(
            @PathVariable Long companyId,
            @Valid @RequestBody CompanyBranchRequest request) {

        return branchService.createBranch(companyId, request);
    }

    @GetMapping("/admin")
    public List<CompanyBranchResponse> getBranches(
            @PathVariable Long companyId) {

        return branchService.getBranches(companyId);
    }

    @PutMapping("/{branchId}/admin")
    public CompanyBranchResponse updateBranch(
            @PathVariable Long companyId,
            @PathVariable Long branchId,
            @Valid @RequestBody CompanyBranchRequest request) {

        return branchService.updateBranch(companyId, branchId, request);
    }

    @DeleteMapping("/{branchId}/admin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBranch(
            @PathVariable Long companyId,
            @PathVariable Long branchId) {

        branchService.deleteBranch(companyId, branchId);
    }
}
