package com.grad.ventora.about.entity;
import jakarta.persistence.*; import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
@Entity @Table(name="about_statistics") @Getter @Setter @NoArgsConstructor
public class AboutStatistic {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="about_page_id", nullable=false) private AboutPage aboutPage;
 @Column(nullable=false,length=40) private String value;
 @Column(nullable=false,length=100) private String label;
 private int displayOrder;
}
