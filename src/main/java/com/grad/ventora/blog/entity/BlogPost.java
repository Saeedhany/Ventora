package com.grad.ventora.blog.entity;
import jakarta.persistence.*; import lombok.*; import org.hibernate.annotations.JdbcTypeCode; import org.hibernate.type.SqlTypes; import java.time.LocalDateTime; import java.util.*;
@Entity @Table(name="blog_posts", indexes={@Index(name="idx_blog_status_published",columnList="status,publishedAt"),@Index(name="idx_blog_featured",columnList="featured")}) @Getter @Setter @NoArgsConstructor
public class BlogPost {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=250) private String title; @Column(nullable=false,unique=true,length=280) private String slug;
 @Column(columnDefinition="TEXT") private String excerpt; @Column(length=1000) private String coverImageUrl;
 @JdbcTypeCode(SqlTypes.JSON) @Column(columnDefinition="json",nullable=false) private List<Map<String,Object>> content=new ArrayList<>();
 @Column(nullable=false,length=150) private String authorName; @Column(length=1000) private String authorImageUrl;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="category_id",nullable=false) private BlogCategory category;
 @Column(nullable=false) private Integer readingTime=1; @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private BlogPostStatus status=BlogPostStatus.DRAFT;
 @Column(nullable=false) private Boolean featured=false; private LocalDateTime publishedAt;
 @Column(nullable=false,updatable=false) private LocalDateTime createdAt; @Column(nullable=false) private LocalDateTime updatedAt;
 @PrePersist void create(){var n=LocalDateTime.now();createdAt=n;updatedAt=n;} @PreUpdate void update(){updatedAt=LocalDateTime.now();}
}
