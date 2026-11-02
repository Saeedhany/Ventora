package com.grad.ventora.homepage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "home_page_contents", indexes = {
        @Index(name = "idx_home_page_locale_status", columnList = "locale,status"),
        @Index(name = "idx_home_page_locale_version", columnList = "locale,version")
})
@Getter @Setter @NoArgsConstructor
public class HomePageContent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer version;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 5)
    private HomePageLocale locale = HomePageLocale.EN;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private HomePageContentStatus status = HomePageContentStatus.DRAFT;

    @Column(length = 200)
    private String seoTitle;

    @Column(length = 500)
    private String seoDescription;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime publishedAt;

    @OneToMany(mappedBy = "homePageContent", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("displayOrder ASC")
    private List<HomePageSection> sections = new ArrayList<>();

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (version == null) version = 1;
    }

    public void addSection(HomePageSection section) {
        sections.add(section);
        section.setHomePageContent(this);
    }

    public void removeSection(HomePageSection section) {
        sections.remove(section);
        section.setHomePageContent(null);
    }
}
