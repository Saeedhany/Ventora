
package com.grad.ventora.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "management_team_members")
@Getter
@Setter
public class ManagementTeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long companyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private ManagementMemberType memberType;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 150)
    private String department;

    @Column(length = 150)
    private String position;

    @Column(length = 1000)
    private String responsibilities;

    @Column(precision = 5, scale = 2)
    private BigDecimal ownershipShare;

    @Column(length = 50)
    private String operationalInvolvement;

    @Column(nullable = false)
    private boolean active = true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
