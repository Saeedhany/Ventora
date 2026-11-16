package com.grad.ventora.blog.repository;
import com.grad.ventora.blog.entity.BlogCategory; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface BlogCategoryRepository extends JpaRepository<BlogCategory,Long> { List<BlogCategory> findAllByActiveTrueOrderByDisplayOrderAsc(); List<BlogCategory> findAllByOrderByDisplayOrderAsc(); Optional<BlogCategory> findBySlugAndActiveTrue(String slug); boolean existsBySlug(String slug); boolean existsByNameIgnoreCase(String name); }
