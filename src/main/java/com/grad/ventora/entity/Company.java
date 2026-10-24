
package com.grad.ventora.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "companies")
@Getter @Setter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150)
    private String companyName;

    @Column(length = 200)
    private String legalName;

    @Column(length = 100)
    private String industry;

    @Column(length = 50)
    private String companySize;

    @Column()
    private Integer foundedYear;

    @Column(length = 255)
    private String website;

    @Column(length = 100)
    private String headquartersCountry;

    @Column(length = 100)
    private String headquartersCity;

    @Column(length = 5000)
    private String businessDescription;

    @Column(length = 500)
    private String companyLogoUrl;

    @Column(length = 100)
    private String legalEntityType;

    @Column(length = 100)
    private String countryOfIncorporation;

    @Column(length = 150)
    private String mainBusinessDomain;

    @Column(length = 150)
    private String primaryBusinessModel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private CompanyProfileStatus profileStatus = CompanyProfileStatus.DRAFT;

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

    @Enumerated(EnumType.STRING)
    @Column(length = 40)
    private OrganizationModel organizationModel;


    @Column(length = 500)
    private String linkedInUrl;

    @Column(length = 500)
    private String twitterUrl;

    @Column(length = 500)
    private String youTubeUrl;

}
