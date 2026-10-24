
package com.grad.ventora.dto;

import java.time.LocalDateTime;
import java.util.List;

public record BusinessSettingsResponse(
        Long id,
        Long companyId,
        String primaryCurrency,
        String fiscalYearStart,
        String timeZone,
        List<String> workingDays,
        String defaultReportLanguage,
        String dateFormat,
        String numberFormat,
        String firstDayOfWeek,
        Boolean enableMultiCurrency,
        Boolean enableBudgeting,
        Boolean enableAdvancedReporting,
        Boolean enableNotifications,
        String businessDescription,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
