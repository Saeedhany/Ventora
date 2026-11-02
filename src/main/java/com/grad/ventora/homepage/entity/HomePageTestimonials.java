package com.grad.ventora.homepage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "home_page_testimonials_sections") @Getter @Setter @NoArgsConstructor
public class HomePageTestimonials {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @OneToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "section_id", nullable = false, unique = true) private HomePageSection section;
    @Column(length = 200) private String title;
    @OneToMany(mappedBy = "testimonials", cascade = CascadeType.ALL, orphanRemoval = true) @OrderBy("displayOrder ASC") private List<HomePageTestimonial> items = new ArrayList<>();
}
