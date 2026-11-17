package com.grad.ventora.documentation.entity;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Table(name="documentation_categories") @Getter @Setter @NoArgsConstructor
public class DocumentationCategory {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=120) private String name;
 @Column(nullable=false,unique=true,length=140) private String slug;
 @Column(length=500) private String description;
 @Column(nullable=false) private Integer displayOrder=0;
 @Column(nullable=false) private Boolean active=true;
 @Column(nullable=false,updatable=false) private LocalDateTime createdAt;
 @Column(nullable=false) private LocalDateTime updatedAt;
 @PrePersist void prePersist(){var n=LocalDateTime.now();createdAt=n;updatedAt=n;}
 @PreUpdate void preUpdate(){updatedAt=LocalDateTime.now();}
}
