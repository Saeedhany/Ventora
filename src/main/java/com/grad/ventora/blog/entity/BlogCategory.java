package com.grad.ventora.blog.entity;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Table(name="blog_categories") @Getter @Setter @NoArgsConstructor
public class BlogCategory {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,unique=true,length=100) private String name;
 @Column(nullable=false,unique=true,length=120) private String slug;
 @Column(length=500) private String description;
 @Column(nullable=false) private Integer displayOrder=0;
 @Column(nullable=false) private Boolean active=true;
 @Column(nullable=false,updatable=false) private LocalDateTime createdAt; @Column(nullable=false) private LocalDateTime updatedAt;
 @PrePersist void create(){var n=LocalDateTime.now();createdAt=n;updatedAt=n;} @PreUpdate void update(){updatedAt=LocalDateTime.now();}
}
