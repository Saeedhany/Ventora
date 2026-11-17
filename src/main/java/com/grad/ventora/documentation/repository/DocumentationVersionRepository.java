package com.grad.ventora.documentation.repository;
import com.grad.ventora.documentation.entity.DocumentationVersion; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface DocumentationVersionRepository extends JpaRepository<DocumentationVersion,Long>{ Optional<DocumentationVersion> findBySlug(String slug); Optional<DocumentationVersion> findFirstByIsLatestTrueAndActiveTrue(); boolean existsBySlug(String slug); }
