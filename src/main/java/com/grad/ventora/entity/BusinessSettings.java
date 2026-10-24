
package com.grad.ventora.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(
        name = "business_settings",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_business_settings_company",
                        columnNames = "company_id"
                )
        }
)
public class BusinessSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(length = 3)
    private String primaryCurrency;

    @Column(length = 5)
    private String fiscalYearStart;

    @Column(length = 100)
    private String timeZone;

    @Column(columnDefinition = "JSON")
    private String workingDays;

    @Column(length = 10)
    private String defaultReportLanguage;

    @Column(length = 20)
    private String dateFormat;

    @Column(length = 20)
    private String numberFormat;

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private DayOfWeek firstDayOfWeek;

    @Column(nullable = false)
    private boolean enableMultiCurrency = false;

    @Column(nullable = false)
    private boolean enableBudgeting = false;

    @Column(nullable = false)
    private boolean enableAdvancedReporting = false;

    @Column(nullable = false)
    private boolean enableNotifications = true;

    @Column(length = 5000)
    private String businessDescription;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum DayOfWeek {
        SUNDAY,
        MONDAY,
        TUESDAY,
        WEDNESDAY,
        THURSDAY,
        FRIDAY,
        SATURDAY
    }
}
