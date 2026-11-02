package com.grad.ventora.homepage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "home_page_footers") @Getter @Setter @NoArgsConstructor
public class HomePageFooter {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @OneToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "section_id", nullable = false, unique = true) private HomePageSection section;
    @Column(length = 250) private String tagline;
    @Column(length = 250) private String newsletterTitle;
    @Column(length = 250) private String newsletterDescription;
    @Column(length = 150) private String newsletterButtonLabel;
    @Column(length = 500) private String copyrightText;
    @OneToMany(mappedBy = "footer", cascade = CascadeType.ALL, orphanRemoval = true) @OrderBy("displayOrder ASC") private List<HomePageFooterLink> links = new ArrayList<>();
}
