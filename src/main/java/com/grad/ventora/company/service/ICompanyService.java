
package com.grad.ventora.company.service;

import com.grad.ventora.dto.CompanyProfileRequest;
import com.grad.ventora.dto.CompanyProfileResponse;

import java.util.List;

public interface ICompanyService {

    CompanyProfileResponse createDraft(CompanyProfileRequest request);

    CompanyProfileResponse getCompanyById(Long companyId);

    CompanyProfileResponse updateDraft(
            Long companyId,
            CompanyProfileRequest request
    );

    List<CompanyProfileResponse> getAllCompanies();
}
