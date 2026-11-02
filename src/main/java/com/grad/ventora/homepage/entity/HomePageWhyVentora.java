package com.grad.ventora.homepage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "home_page_why_ventora") @Getter @Setter @NoArgsConstructor
public class HomePageWhyVentora {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @OneToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "section_id", nullable = false, unique = true) private HomePageSection section;
    @Column(length = 200) private String title;
    @Column(columnDefinition = "TEXT") private String description;
    @OneToMany(mappedBy = "whyVentora", cascade = CascadeType.ALL, orphanRemoval = true) @OrderBy("displayOrder ASC") private List<HomePageFeature> features = new ArrayList<>();
    @OneToMany(mappedBy = "whyVentora", cascade = CascadeType.ALL, orphanRemoval = true) @OrderBy("displayOrder ASC") private List<HomePageStatistic> statistics = new ArrayList<>();
}
