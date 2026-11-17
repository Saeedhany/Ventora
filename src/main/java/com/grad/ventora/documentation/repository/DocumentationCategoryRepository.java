package com.grad.ventora.documentation.repository;
import com.grad.ventora.documentation.entity.DocumentationCategory; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface DocumentationCategoryRepository extends JpaRepository<DocumentationCategory,Long>{ List<DocumentationCategory> findAllByActiveTrueOrderByDisplayOrderAsc(); List<DocumentationCategory> findAllByOrderByDisplayOrderAsc(); boolean existsBySlug(String slug); boolean existsByNameIgnoreCase(String name); }
