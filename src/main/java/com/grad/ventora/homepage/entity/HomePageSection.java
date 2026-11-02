package com.grad.ventora.homepage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "home_page_sections", indexes = @Index(name = "idx_home_section_page_order", columnList = "home_page_content_id,display_order"))
@Getter @Setter @NoArgsConstructor
public class HomePageSection {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "home_page_content_id", nullable = false)
    private HomePageContent homePageContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "section_type", nullable = false, length = 30)
    private HomePageSectionType sectionType;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder = 0;

    @Column(nullable = false)
    private boolean visible = true;
}
