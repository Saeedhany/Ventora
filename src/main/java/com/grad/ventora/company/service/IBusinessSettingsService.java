
package com.grad.ventora.company.service;

import com.grad.ventora.dto.BusinessSettingsRequest;
import com.grad.ventora.dto.BusinessSettingsResponse;

public interface IBusinessSettingsService {

    BusinessSettingsResponse getSettings(Long companyId);

    BusinessSettingsResponse saveSettings(
            Long companyId,
            BusinessSettingsRequest request
    );

    BusinessSettingsResponse updateSettings(
            Long companyId,
            BusinessSettingsRequest request
    );
}
