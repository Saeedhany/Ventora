package com.grad.ventora.homepage.repository;
import com.grad.ventora.homepage.entity.HomePageFooter;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface HomePageFooterRepository extends JpaRepository<HomePageFooter, Long> {
    Optional<HomePageFooter> findBySectionId(Long sectionId);
}
