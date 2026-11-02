package com.grad.ventora.homepage.repository;
import com.grad.ventora.homepage.entity.HomePageTestimonials;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface HomePageTestimonialsRepository extends JpaRepository<HomePageTestimonials, Long> {
    Optional<HomePageTestimonials> findBySectionId(Long sectionId);
}
