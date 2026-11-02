package com.grad.ventora.homepage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "home_page_testimonials") @Getter @Setter @NoArgsConstructor
public class HomePageTestimonial {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "testimonials_section_id", nullable = false) private HomePageTestimonials testimonials;
    @Column(nullable = false, length = 150) private String personName;
    @Column(length = 150) private String roleOrCompany;
    @Column(nullable = false, columnDefinition = "TEXT") private String quote;
    @Column(length = 1000) private String photoUrl;
    @Column(nullable = false) private Integer displayOrder = 0;
}
