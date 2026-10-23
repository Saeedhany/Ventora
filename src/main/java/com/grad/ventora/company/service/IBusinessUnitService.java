
package com.grad.ventora.company.service;

import com.grad.ventora.dto.BusinessUnitRequest;
import com.grad.ventora.dto.BusinessUnitResponse;

import java.util.List;

public interface IBusinessUnitService {

    BusinessUnitResponse createBusinessUnit(
            Long companyId,
            BusinessUnitRequest request
    );

    List<BusinessUnitResponse> getBusinessUnits(Long companyId);

    BusinessUnitResponse updateBusinessUnit(
            Long companyId,
            Long unitId,
            BusinessUnitRequest request
    );

    void deleteBusinessUnit(Long companyId, Long unitId);
}
