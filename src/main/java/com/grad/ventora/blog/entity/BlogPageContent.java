package com.grad.ventora.blog.entity;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime;
@Entity @Table(name="blog_page_content") @Getter @Setter @NoArgsConstructor
public class BlogPageContent { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(length=250) private String heroTitle; @Column(columnDefinition="TEXT") private String heroDescription;
 @Column(length=250) private String blogTitle; @Column(columnDefinition="TEXT") private String blogDescription;
 @Column(length=250) private String newsletterTitle; @Column(columnDefinition="TEXT") private String newsletterDescription;
 @Column(length=250) private String bottomCtaTitle; @Column(columnDefinition="TEXT") private String bottomCtaDescription;
 @Column(nullable=false,updatable=false) private LocalDateTime createdAt; @Column(nullable=false) private LocalDateTime updatedAt;
 @PrePersist void create(){var n=LocalDateTime.now();createdAt=n;updatedAt=n;} @PreUpdate void update(){updatedAt=LocalDateTime.now();} }
