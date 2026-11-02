package com.grad.ventora.homepage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "home_page_get_started") @Getter @Setter @NoArgsConstructor
public class HomePageGetStarted {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @OneToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "section_id", nullable = false, unique = true) private HomePageSection section;
    @Column(length = 250) private String title;
    @Column(columnDefinition = "TEXT") private String description;
    @Column(length = 100) private String primaryCtaLabel;
    @Column(length = 500) private String primaryCtaUrl;
    @Column(length = 100) private String secondaryCtaLabel;
    @Column(length = 500) private String secondaryCtaUrl;
}
