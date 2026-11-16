package com.grad.ventora.about.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "about_pages")
@Getter @Setter @NoArgsConstructor
public class AboutPage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 200, nullable = false) private String heroTitle;
    @Column(length = 3000) private String heroDescription;
    @Column(length = 100) private String heroValueOne;
    @Column(length = 100) private String heroValueTwo;
    @Column(length = 100) private String heroValueThree;
    @Column(length = 100) private String heroValueFour;
    @Column(length = 200) private String storyTitle;
    @Column(length = 4000) private String storyDescription;
    @Column(length = 500) private String storyVideoUrl;
    @Column(length = 20) private String storyVideoDuration;
    @Column(length = 200) private String missionTitle;
    @Column(length = 3000) private String missionDescription;
    @Column(length = 200) private String visionTitle;
    @Column(length = 3000) private String visionDescription;
    @Column(nullable = false) private boolean published = false;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "aboutPage", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("displayOrder ASC, id ASC") private List<AboutStatistic> statistics = new ArrayList<>();
    @OneToMany(mappedBy = "aboutPage", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("displayOrder ASC, id ASC") private List<AboutValue> values = new ArrayList<>();
    @OneToMany(mappedBy = "aboutPage", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("displayOrder ASC, id ASC") private List<AboutTeamMember> teamMembers = new ArrayList<>();
    @PrePersist void onCreate(){createdAt=LocalDateTime.now();updatedAt=createdAt;}
    @PreUpdate void onUpdate(){updatedAt=LocalDateTime.now();}
}
