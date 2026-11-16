package com.grad.ventora.about.entity;
import jakarta.persistence.*; import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
@Entity @Table(name="about_team_members") @Getter @Setter @NoArgsConstructor
public class AboutTeamMember {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @ManyToOne(fetch=FetchType.LAZY, optional=false) @JoinColumn(name="about_page_id", nullable=false) private AboutPage aboutPage;
 @Column(nullable=false,length=150) private String name;
 @Column(nullable=false,length=120) private String role;
 @Column(length=500) private String photoUrl;
 @Column(length=500) private String linkedInUrl;
 private int displayOrder;
}
