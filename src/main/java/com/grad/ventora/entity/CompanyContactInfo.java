
package com.grad.ventora.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "company_contact_info")
@Getter
@Setter
public class CompanyContactInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long companyId;

    @Column(nullable = false, length = 255)
    private String officialEmail;

    @Column(length = 255)
    private String secondaryEmail;

    @Column(length = 150)
    private String contactPerson;

    @Column(length = 150)
    private String contactTitle;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private PreferredCommunicationChannel preferredChannel;

    @Column(nullable = false, length = 30)
    private String phoneNumber;

    @Column(length = 30)
    private String faxNumber;

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
