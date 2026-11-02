package com.grad.ventora.homepage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "home_page_standalone_statistics")
@Getter
@Setter
@NoArgsConstructor
public class HomePageStandaloneStatistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "statistics_section_id", nullable = false)
    private HomePageStatisticsSection statisticsSection;

    @Column(nullable = false, length = 80)
    private String value;

    @Column(nullable = false, length = 150)
    private String label;

    @Column(nullable = false)
    private Integer displayOrder = 0;
}
