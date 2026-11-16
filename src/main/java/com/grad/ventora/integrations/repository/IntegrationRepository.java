package com.grad.ventora.integrations.repository;
import com.grad.ventora.integrations.entity.Integration;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface IntegrationRepository extends JpaRepository<Integration,Long> {
 List<Integration> findByActiveTrueOrderByDisplayOrderAscIdAsc();
 List<Integration> findAllByOrderByDisplayOrderAscIdAsc();
 Optional<Integration> findBySlugAndActiveTrue(String slug);
 boolean existsByNameIgnoreCase(String name); boolean existsBySlug(String slug);
 boolean existsByNameIgnoreCaseAndIdNot(String name,Long id); boolean existsBySlugAndIdNot(String slug,Long id);
 List<Integration> findDistinctByCategories_SlugAndActiveTrueOrderByDisplayOrderAscIdAsc(String slug);
}
