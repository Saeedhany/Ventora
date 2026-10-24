
package com.grad.ventora.company.service;

import com.grad.ventora.dto.CompanyContactInfoRequest;
import com.grad.ventora.dto.CompanyContactInfoResponse;

public interface ICompanyContactInfoService {

    CompanyContactInfoResponse getContactInfo(Long companyId);

    CompanyContactInfoResponse saveContactInfo(
            Long companyId,
            CompanyContactInfoRequest request
    );

    CompanyContactInfoResponse updateContactInfo(
            Long companyId,
            CompanyContactInfoRequest request
    );
}
