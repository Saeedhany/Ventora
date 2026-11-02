package com.grad.ventora.homepage.repository;
import com.grad.ventora.homepage.entity.HomePageWhyVentora;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface HomePageWhyVentoraRepository extends JpaRepository<HomePageWhyVentora, Long> {
    Optional<HomePageWhyVentora> findBySectionId(Long sectionId);
}
