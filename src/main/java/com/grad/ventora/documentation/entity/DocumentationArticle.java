package com.grad.ventora.documentation.entity;
import jakarta.persistence.*; import lombok.*; import java.time.LocalDateTime; import java.util.*;
@Entity @Table(name="documentation_articles",indexes={@Index(name="idx_doc_article_version_status",columnList="version_id,status"),@Index(name="idx_doc_article_category",columnList="category_id")}) @Getter @Setter @NoArgsConstructor
public class DocumentationArticle {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=250) private String title;
 @Column(nullable=false,length=180) private String slug;
 @Column(length=1000) private String description;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="category_id",nullable=false) private DocumentationCategory category;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="version_id",nullable=false) private DocumentationVersion version;
 @Column(nullable=false) private Integer displayOrder=0;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private DocumentationArticleStatus status=DocumentationArticleStatus.DRAFT;
 private LocalDateTime publishedAt;
 @OneToMany(mappedBy="article",cascade=CascadeType.ALL,orphanRemoval=true) @OrderBy("displayOrder ASC") private List<DocumentationContentBlock> contentBlocks=new ArrayList<>();
 @ManyToMany @JoinTable(name="documentation_related_articles",joinColumns=@JoinColumn(name="article_id"),inverseJoinColumns=@JoinColumn(name="related_article_id")) private Set<DocumentationArticle> relatedArticles=new LinkedHashSet<>();
 @Column(nullable=false,updatable=false) private LocalDateTime createdAt;
 @Column(nullable=false) private LocalDateTime updatedAt;
 @PrePersist void prePersist(){var n=LocalDateTime.now();createdAt=n;updatedAt=n;}
 @PreUpdate void preUpdate(){updatedAt=LocalDateTime.now();}
}
