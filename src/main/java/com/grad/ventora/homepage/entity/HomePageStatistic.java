package com.grad.ventora.homepage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "home_page_statistics") @Getter @Setter @NoArgsConstructor
public class HomePageStatistic {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "why_ventora_id", nullable = false) private HomePageWhyVentora whyVentora;
    @Column(nullable = false, length = 80) private String value;
    @Column(nullable = false, length = 150) private String label;
    @Column(nullable = false) private Integer displayOrder = 0;
}
