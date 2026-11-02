package com.grad.ventora.homepage.repository;
import com.grad.ventora.homepage.entity.HomePageFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface HomePageFeatureRepository extends JpaRepository<HomePageFeature, Long> {
    List<HomePageFeature> findByWhyVentoraIdOrderByDisplayOrderAsc(Long whyVentoraId);
}
