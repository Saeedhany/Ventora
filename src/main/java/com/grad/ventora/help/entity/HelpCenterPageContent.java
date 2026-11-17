package com.grad.ventora.help.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.LocalDateTime;
import java.util.*;
@Entity
@Table(name="help_center_page_content")
@Getter @Setter @NoArgsConstructor
public class HelpCenterPageContent {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(length=250) private String heroTitle;
    @Column(columnDefinition="TEXT") private String heroDescription;
    @Column(length=500) private String searchPlaceholder;
    @Column(length=250) private String quickAccessTitle;
    @Column(columnDefinition="TEXT") private String quickAccessDescription;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition="json")
    private List<Map<String,Object>> quickAccessCards=new ArrayList<>();
    @Column(length=250) private String faqTitle;
    @Column(columnDefinition="TEXT") private String faqDescription;
    @Column(length=250) private String stillNeedHelpTitle;
    @Column(columnDefinition="TEXT") private String stillNeedHelpDescription;
    @Column(length=250) private String contactSupportLabel;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition="json")
    private List<Map<String,Object>> otherSupportWays=new ArrayList<>();
    @Column(length=250) private String moreResourcesTitle;
    @Column(columnDefinition="TEXT") private String moreResourcesDescription;
    @Column(length=250) private String browseResourcesLabel;
    @Column(nullable=false, updatable=false) private LocalDateTime createdAt;
    @Column(nullable=false) private LocalDateTime updatedAt;
    @PrePersist
    void prePersist(){ LocalDateTime now=LocalDateTime.now(); createdAt=now; updatedAt=now; }
    @PreUpdate
    void preUpdate(){ updatedAt=LocalDateTime.now(); }
}
