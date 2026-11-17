package com.grad.ventora.documentation.entity;
import jakarta.persistence.*; import lombok.*; import org.hibernate.annotations.JdbcTypeCode; import org.hibernate.type.SqlTypes; import java.util.Map;
@Entity @Table(name="documentation_content_blocks",indexes=@Index(name="idx_doc_block_article_order",columnList="article_id,displayOrder")) @Getter @Setter @NoArgsConstructor
public class DocumentationContentBlock {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="article_id",nullable=false) private DocumentationArticle article;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=30) private DocumentationBlockType type;
 @JdbcTypeCode(SqlTypes.JSON) @Column(nullable=false,columnDefinition="json") private Map<String,Object> content;
 @Column(nullable=false) private Integer displayOrder=0;
}
