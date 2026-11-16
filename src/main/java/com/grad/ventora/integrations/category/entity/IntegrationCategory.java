package com.grad.ventora.integrations.category.entity;

import com.grad.ventora.integrations.entity.Integration;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "integration_category", uniqueConstraints = {
    @UniqueConstraint(columnNames = "name"), @UniqueConstraint(columnNames = "slug")
})
@Getter @Setter @NoArgsConstructor
public class IntegrationCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, length=100) private String name;
    @Column(nullable=false, length=120) private String slug;
    @Column(columnDefinition="TEXT") private String description;
    @Column(length=500) private String iconUrl;
    @Column(nullable=false) private Integer displayOrder=0;
    @Column(nullable=false) private boolean active=true;
    @Column(nullable=false, updatable=false) private LocalDateTime createdAt;
    @Column(nullable=false) private LocalDateTime updatedAt;
    @ManyToMany(mappedBy="categories") private Set<Integration> integrations = new HashSet<>();
    @PrePersist protected void onCreate() { LocalDateTime now=LocalDateTime.now(); createdAt=now; updatedAt=now; }
    @PreUpdate protected void onUpdate() { updatedAt=LocalDateTime.now(); }
}
