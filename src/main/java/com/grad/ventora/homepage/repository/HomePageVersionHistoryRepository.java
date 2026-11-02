package com.grad.ventora.homepage.repository;

import com.grad.ventora.homepage.entity.HomePageVersionHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HomePageVersionHistoryRepository extends JpaRepository<HomePageVersionHistory, Long> {
    List<HomePageVersionHistory> findByHomePageContent_LocaleOrderByCreatedAtDesc(com.grad.ventora.homepage.entity.HomePageLocale locale);
    List<HomePageVersionHistory> findByHomePageContentIdOrderByCreatedAtDesc(Long homePageContentId);
}
