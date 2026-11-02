package com.grad.ventora.homepage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "home_page_heroes") @Getter @Setter @NoArgsConstructor
public class HomePageHero {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @OneToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "section_id", nullable = false, unique = true) private HomePageSection section;
    @Column(nullable = false, length = 250) private String title;
    @Column(columnDefinition = "TEXT") private String body;
    @Column(length = 100) private String primaryCtaLabel;
    @Column(length = 500) private String primaryCtaUrl;
    @Column(length = 100) private String secondaryCtaLabel;
    @Column(length = 500) private String secondaryCtaUrl;
    @Column(length = 1000) private String imageUrl;
    @Column(length = 250) private String imageAltText;
}
