
package com.grad.ventora.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "company_branches")
@Getter
@Setter
public class CompanyBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long companyId;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false)
    private boolean headquarters = false;

    @Column(nullable = false, length = 255)
    private String addressLine;

    @Column(length = 100)
    private String city;

    @Column(length = 100)
    private String stateOrRegion;

    @Column(length = 30)
    private String postalCode;

    @Column(length = 100)
    private String country;

    @Column(length = 150)
    private String contactName;

    @Column(length = 150)
    private String contactTitle;

    @Column(length = 255)
    private String email;

    @Column(length = 30)
    private String phoneNumber;

    @Column(nullable = false)
    private boolean active = true;

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
