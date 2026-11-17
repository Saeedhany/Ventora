package com.grad.ventora.help.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
@Entity
@Table(name="help_faqs", indexes={
    @Index(name="idx_help_faq_status_order", columnList="status,displayOrder"),
    @Index(name="idx_help_faq_category", columnList="category_id")
})
@Getter @Setter @NoArgsConstructor
public class HelpFaq {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false, length=500)
    private String question;
    @Column(nullable=false, columnDefinition="TEXT")
    private String answer;
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="category_id", nullable=false)
    private HelpCategory category;
    @Column(nullable=false)
    private Integer displayOrder=0;
    @Enumerated(EnumType.STRING)
    @Column(nullable=false, length=20)
    private HelpFaqStatus status=HelpFaqStatus.DRAFT;
    @Column(nullable=false)
    private Boolean featured=false;
    @Column(nullable=false, updatable=false)
    private LocalDateTime createdAt;
    @Column(nullable=false)
    private LocalDateTime updatedAt;
    @PrePersist
    void prePersist(){ LocalDateTime now=LocalDateTime.now(); createdAt=now; updatedAt=now; }
    @PreUpdate
    void preUpdate(){ updatedAt=LocalDateTime.now(); }
}
