package com.grad.ventora.homepage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table(name = "home_page_footer_links") @Getter @Setter @NoArgsConstructor
public class HomePageFooterLink {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "footer_id", nullable = false) private HomePageFooter footer;
    @Column(nullable = false, length = 120) private String label;
    @Column(nullable = false, length = 500) private String url;
    @Column(length = 100) private String groupName;
    @Column(nullable = false) private Integer displayOrder = 0;
}
