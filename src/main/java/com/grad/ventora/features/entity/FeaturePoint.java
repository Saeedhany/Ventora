package com.grad.ventora.features.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "feature_points")
@Getter @Setter @NoArgsConstructor
public class FeaturePoint {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "feature_id", nullable = false)
    private Feature feature;
    @Column(nullable = false, length = 300)
    private String content;
    @Column(nullable = false)
    private Integer displayOrder = 0;
}
