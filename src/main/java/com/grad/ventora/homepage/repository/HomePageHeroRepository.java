package com.grad.ventora.homepage.repository;
import com.grad.ventora.homepage.entity.HomePageHero;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface HomePageHeroRepository extends JpaRepository<HomePageHero, Long> {
    Optional<HomePageHero> findBySectionId(Long sectionId);
}
