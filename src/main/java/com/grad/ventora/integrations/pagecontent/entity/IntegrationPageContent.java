package com.grad.ventora.integrations.pagecontent.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "integration_page_content")
@Getter
@Setter
@NoArgsConstructor
public class IntegrationPageContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Hero section
    @Column(length = 250) private String heroTitle;
    @Column(columnDefinition = "TEXT") private String heroDescription;
    @Column(length = 500) private String heroImageUrl;
    @Column(length = 120) private String heroButtonLabel;
    @Column(length = 500) private String heroButtonUrl;

    // Popular integrations section heading and description
    @Column(length = 250) private String popularTitle;
    @Column(columnDefinition = "TEXT") private String popularDescription;

    // Easy setup section
    @Column(length = 250) private String easySetupTitle;
    @Column(columnDefinition = "TEXT") private String easySetupDescription;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<String> easySetupPoints = new ArrayList<>();
    @Column(length = 120) private String easySetupButtonLabel;
    @Column(length = 500) private String easySetupButtonUrl;

    // Categories section heading and description
    @Column(length = 250) private String categoriesTitle;
    @Column(columnDefinition = "TEXT") private String categoriesDescription;

    // Bottom call-to-action
    @Column(length = 250) private String bottomCtaTitle;
    @Column(columnDefinition = "TEXT") private String bottomCtaDescription;
    @Column(length = 120) private String bottomCtaButtonLabel;
    @Column(length = 500) private String bottomCtaButtonUrl;

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
}
