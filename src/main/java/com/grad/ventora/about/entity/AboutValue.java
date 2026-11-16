package com.grad.ventora.about.entity;
import jakarta.persistence.*; import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
@Entity @Table(name="about_values") @Getter @Setter @NoArgsConstructor
public class AboutValue {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="about_page_id", nullable=false) private AboutPage aboutPage;
 @Column(nullable=false,length=120) private String title;
 @Column(length=1000) private String description;
 private int displayOrder;
}
