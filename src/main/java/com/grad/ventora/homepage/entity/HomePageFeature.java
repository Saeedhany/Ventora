package com.grad.ventora.homepage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "home_page_features") @Getter @Setter @NoArgsConstructor
public class HomePageFeature {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "why_ventora_id", nullable = false) private HomePageWhyVentora whyVentora;
    @Column(nullable = false, length = 150) private String title;
    @Column(columnDefinition = "TEXT") private String description;
    @Column(length = 100) private String icon;
    @Column(nullable = false) private Integer displayOrder = 0;
}
