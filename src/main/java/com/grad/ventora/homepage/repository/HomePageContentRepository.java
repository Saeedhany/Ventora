package com.grad.ventora.homepage.repository;

import com.grad.ventora.homepage.entity.HomePageContent;
import com.grad.ventora.homepage.entity.HomePageContentStatus;
import com.grad.ventora.homepage.entity.HomePageLocale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;

public interface HomePageContentRepository extends JpaRepository<HomePageContent, Long> {
    Optional<HomePageContent> findFirstByLocaleAndStatusOrderByVersionDesc(HomePageLocale locale, HomePageContentStatus status);
    List<HomePageContent> findByLocaleOrderByVersionDesc(HomePageLocale locale);
    boolean existsByLocaleAndStatus(HomePageLocale locale, HomePageContentStatus status);

    @Query("select coalesce(max(h.version), 0) from HomePageContent h where h.locale = :locale")
    int findMaxVersionByLocale(@Param("locale") HomePageLocale locale);
}
