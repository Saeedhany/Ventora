
package com.grad.ventora.dto;

import jakarta.validation.constraints.Size;

import java.util.List;

public record BusinessSettingsRequest(

        @Size(max = 3)
        String primaryCurrency,

        @Size(max = 5)
        String fiscalYearStart,

        @Size(max = 100)
        String timeZone,

        List<String> workingDays,

        @Size(max = 10)
        String defaultReportLanguage,

        @Size(max = 20)
        String dateFormat,

        @Size(max = 20)
        String numberFormat,

        String firstDayOfWeek,

        Boolean enableMultiCurrency,

        Boolean enableBudgeting,

        Boolean enableAdvancedReporting,

        Boolean enableNotifications,

        @Size(max = 5000)
        String businessDescription
) {
}
