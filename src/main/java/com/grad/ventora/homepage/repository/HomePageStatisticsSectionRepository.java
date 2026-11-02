package com.grad.ventora.homepage.repository;

import com.grad.ventora.homepage.entity.HomePageStatisticsSection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HomePageStatisticsSectionRepository
        extends JpaRepository<HomePageStatisticsSection, Long> {

    Optional<HomePageStatisticsSection> findBySectionId(Long sectionId);
}
