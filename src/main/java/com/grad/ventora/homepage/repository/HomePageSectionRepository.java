package com.grad.ventora.homepage.repository;

import com.grad.ventora.homepage.entity.HomePageSection;
import com.grad.ventora.homepage.entity.HomePageContent;
import com.grad.ventora.homepage.entity.HomePageSectionType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HomePageSectionRepository extends JpaRepository<HomePageSection, Long> {
    List<HomePageSection> findByHomePageContentOrderByDisplayOrderAsc(HomePageContent homePageContent);
    boolean existsByHomePageContentIdAndSectionType(Long homePageContentId, HomePageSectionType sectionType);
}
