package com.grad.ventora.documentation.entity;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Table(name="documentation_page_content") @Getter @Setter @NoArgsConstructor
public class DocumentationPageContent {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(length=300) private String heroTitle; @Column(length=1000) private String heroDescription;
 @Column(length=200) private String searchPlaceholder; @Column(length=300) private String needHelpTitle;
 @Column(length=1000) private String needHelpDescription; @Column(length=100) private String getStartedLabel;
 @Column(length=500) private String getStartedUrl;
 @Column(nullable=false,updatable=false) private LocalDateTime createdAt; @Column(nullable=false) private LocalDateTime updatedAt;
 @PrePersist void prePersist(){var n=LocalDateTime.now();createdAt=n;updatedAt=n;} @PreUpdate void preUpdate(){updatedAt=LocalDateTime.now();}
}
