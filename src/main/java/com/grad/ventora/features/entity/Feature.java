package com.grad.ventora.features.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "features")
@Getter @Setter @NoArgsConstructor
public class Feature {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 120)
    private String name;
    @Column(nullable = false, unique = true, length = 150)
    private String slug;
    @Column(nullable = false, length = 150)
    private String sectionLabel;
    @Column(nullable = false, length = 250)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(length = 500)
    private String imageUrl;
    @Column(length = 100)
    private String ctaLabel;
    @Column(length = 500)
    private String ctaUrl;
    @Column(nullable = false)
    private Integer displayOrder = 0;
    @Column(nullable = false)
    private boolean active = true;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "feature", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("displayOrder ASC, id ASC")
    private List<FeaturePoint> points = new ArrayList<>();

    @OneToMany(mappedBy = "feature", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("displayOrder ASC, id ASC")
    private List<FeatureSection> sections = new ArrayList<>();


    public void addPoint(FeaturePoint point) {
        points.add(point);
        point.setFeature(this);
    }
    public void clearPoints() { points.clear(); }
    public void addSection(FeatureSection section) {
        sections.add(section);
        section.setFeature(this);
    }
    public void clearSections() { sections.clear(); }
    @PrePersist protected void onCreate() {
        LocalDateTime now = LocalDateTime.now(); createdAt = now; updatedAt = now;
    }
    @PreUpdate protected void onUpdate() { updatedAt = LocalDateTime.now(); }
}
