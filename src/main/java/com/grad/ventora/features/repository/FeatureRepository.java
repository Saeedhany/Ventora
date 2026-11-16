package com.grad.ventora.features.repository;
import com.grad.ventora.features.entity.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findByActiveTrueOrderByDisplayOrderAscIdAsc();
    List<Feature> findAllByOrderByDisplayOrderAscIdAsc();
    Optional<Feature> findByIdAndActiveTrue(Long id);
    Optional<Feature> findBySlugAndActiveTrue(String slug);
    boolean existsByNameIgnoreCase(String name);
    boolean existsBySlug(String slug);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
    boolean existsBySlugAndIdNot(String slug, Long id);
}
