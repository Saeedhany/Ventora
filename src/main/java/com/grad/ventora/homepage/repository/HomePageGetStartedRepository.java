package com.grad.ventora.homepage.repository;
import com.grad.ventora.homepage.entity.HomePageGetStarted;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface HomePageGetStartedRepository extends JpaRepository<HomePageGetStarted, Long> {
    Optional<HomePageGetStarted> findBySectionId(Long sectionId);
}
