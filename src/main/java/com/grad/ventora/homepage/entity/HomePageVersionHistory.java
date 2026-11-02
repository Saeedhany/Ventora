package com.grad.ventora.homepage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity @Table(name = "home_page_version_history", indexes = @Index(name = "idx_home_history_created_at", columnList = "created_at"))
@Getter @Setter @NoArgsConstructor
public class HomePageVersionHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "home_page_content_id", nullable = false) private HomePageContent homePageContent;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20) private HomePageHistoryAction action;
    @Column(length = 500) private String releaseNote;
    @Column(length = 150) private String actor;
    @Column(name = "created_at", nullable = false, updatable = false) private LocalDateTime createdAt;
    @PrePersist protected void onCreate() { if (createdAt == null) createdAt = LocalDateTime.now(); }
}
