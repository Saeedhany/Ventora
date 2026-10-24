
package com.grad.ventora.company.service;

import com.grad.ventora.dto.CompanyBranchRequest;
import com.grad.ventora.dto.CompanyBranchResponse;

import java.util.List;

public interface ICompanyBranchService {

    CompanyBranchResponse createBranch(
            Long companyId,
            CompanyBranchRequest request
    );

    List<CompanyBranchResponse> getBranches(Long companyId);

    CompanyBranchResponse updateBranch(
            Long companyId,
            Long branchId,
            CompanyBranchRequest request
    );

    void deleteBranch(Long companyId, Long branchId);
}
