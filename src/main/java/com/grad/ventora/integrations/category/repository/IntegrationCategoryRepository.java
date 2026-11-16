package com.grad.ventora.integrations.category.repository;
import com.grad.ventora.integrations.category.entity.IntegrationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;
public interface IntegrationCategoryRepository extends JpaRepository<IntegrationCategory,Long> {
 List<IntegrationCategory> findByActiveTrueOrderByDisplayOrderAscIdAsc();
 List<IntegrationCategory> findAllByOrderByDisplayOrderAscIdAsc();
 Optional<IntegrationCategory> findBySlugAndActiveTrue(String slug);
 boolean existsByNameIgnoreCase(String name); boolean existsBySlug(String slug);
 boolean existsByNameIgnoreCaseAndIdNot(String name,Long id); boolean existsBySlugAndIdNot(String slug,Long id);
}
