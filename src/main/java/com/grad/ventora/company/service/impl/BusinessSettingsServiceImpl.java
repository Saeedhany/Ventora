
package com.grad.ventora.company.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grad.ventora.company.service.IBusinessSettingsService;
import com.grad.ventora.dto.BusinessSettingsRequest;
import com.grad.ventora.dto.BusinessSettingsResponse;
import com.grad.ventora.entity.BusinessSettings;
import com.grad.ventora.repository.BusinessSettingsRepository;
import com.grad.ventora.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@Transactional
@RequiredArgsConstructor
public class BusinessSettingsServiceImpl implements IBusinessSettingsService {

    private final BusinessSettingsRepository settingsRepository;
    private final CompanyRepository companyRepository;
    private final ObjectMapper objectMapper;



    @Override
    @Transactional(readOnly = true)
    public BusinessSettingsResponse getSettings(Long companyId) {
        BusinessSettings settings = settingsRepository
                .findByCompanyId(companyId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Business settings not found for company: " + companyId
                        )
                );

        return toResponse(settings);
    }

    @Override
    public BusinessSettingsResponse saveSettings(
            Long companyId,
            BusinessSettingsRequest request
    ) {
        if (!companyRepository.existsById(companyId)) {
            throw new IllegalArgumentException(
                    "Company not found: " + companyId
            );
        }

        if (settingsRepository.existsByCompanyId(companyId)) {
            throw new IllegalArgumentException(
                    "Business settings already exist for company: " + companyId
            );
        }

        BusinessSettings settings = new BusinessSettings();
        settings.setCompanyId(companyId);

        applyRequest(settings, request);

        BusinessSettings saved = settingsRepository.save(settings);
        return toResponse(saved);
    }

    @Override
    public BusinessSettingsResponse updateSettings(
            Long companyId,
            BusinessSettingsRequest request
    ) {
        BusinessSettings settings = settingsRepository
                .findByCompanyId(companyId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Business settings not found for company: " + companyId
                        )
                );

        applyRequest(settings, request);

        BusinessSettings updated = settingsRepository.save(settings);
        return toResponse(updated);
    }

    private void applyRequest(
            BusinessSettings settings,
            BusinessSettingsRequest request
    ) {
        settings.setPrimaryCurrency(request.primaryCurrency());
        settings.setFiscalYearStart(request.fiscalYearStart());
        settings.setTimeZone(request.timeZone());

        settings.setWorkingDays(
                toJson(request.workingDays())
        );

        settings.setDefaultReportLanguage(
                request.defaultReportLanguage()
        );
        settings.setDateFormat(request.dateFormat());
        settings.setNumberFormat(request.numberFormat());

        settings.setFirstDayOfWeek(
                parseDayOfWeek(request.firstDayOfWeek())
        );

        if (request.enableMultiCurrency() != null) {
            settings.setEnableMultiCurrency(
                    request.enableMultiCurrency()
            );
        }

        if (request.enableBudgeting() != null) {
            settings.setEnableBudgeting(
                    request.enableBudgeting()
            );
        }

        if (request.enableAdvancedReporting() != null) {
            settings.setEnableAdvancedReporting(
                    request.enableAdvancedReporting()
            );
        }

        if (request.enableNotifications() != null) {
            settings.setEnableNotifications(
                    request.enableNotifications()
            );
        }

        settings.setBusinessDescription(
                request.businessDescription()
        );
    }

    private BusinessSettings.DayOfWeek parseDayOfWeek(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        try {
            return BusinessSettings.DayOfWeek.valueOf(
                    value.trim().toUpperCase(Locale.ROOT)
            );
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(
                    "Invalid firstDayOfWeek: " + value
            );
        }
    }

    private String toJson(List<String> workingDays) {
        if (workingDays == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsString(workingDays);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException(
                    "Could not convert working days to JSON",
                    exception
            );
        }
    }

    private List<String> fromJson(String workingDays) {
        if (workingDays == null || workingDays.isBlank()) {
            return null;
        }

        try {
            return objectMapper.readValue(
                    workingDays,
                    new TypeReference<List<String>>() {}
            );
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException(
                    "Could not read working days from JSON",
                    exception
            );
        }
    }

    private BusinessSettingsResponse toResponse(
            BusinessSettings settings
    ) {
        return new BusinessSettingsResponse(
                settings.getId(),
                settings.getCompanyId(),
                settings.getPrimaryCurrency(),
                settings.getFiscalYearStart(),
                settings.getTimeZone(),
                fromJson(settings.getWorkingDays()),
                settings.getDefaultReportLanguage(),
                settings.getDateFormat(),
                settings.getNumberFormat(),
                settings.getFirstDayOfWeek() == null
                        ? null
                        : settings.getFirstDayOfWeek().name(),
                settings.isEnableMultiCurrency(),
                settings.isEnableBudgeting(),
                settings.isEnableAdvancedReporting(),
                settings.isEnableNotifications(),
                settings.getBusinessDescription(),
                settings.getCreatedAt(),
                settings.getUpdatedAt()
        );
    }
}
