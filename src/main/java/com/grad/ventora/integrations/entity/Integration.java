package com.grad.ventora.integrations.entity;

import com.grad.ventora.integrations.category.entity.IntegrationCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "integrations")
@Getter @Setter @NoArgsConstructor
public class Integration {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 120)
    private String name;
    @Column(nullable = false, unique = true, length = 150)
    private String slug;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Column(length = 500)
    private String logoUrl;
    @Column(length = 500)
    private String websiteUrl;
    @Column(nullable = false)
    private Integer displayOrder = 0;
    @Column(nullable = false)
    private boolean active = true;
    @Column(name = "is_new", nullable = false)
    private boolean isNew = false;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(name = "integration_categories",
        joinColumns = @JoinColumn(name = "integration_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<IntegrationCategory> categories = new HashSet<>();


    @PrePersist protected void onCreate() { LocalDateTime now=LocalDateTime.now(); createdAt=now; updatedAt=now; }
    @PreUpdate protected void onUpdate() { updatedAt=LocalDateTime.now(); }
}
