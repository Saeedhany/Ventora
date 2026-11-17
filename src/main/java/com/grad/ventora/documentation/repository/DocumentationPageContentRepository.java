package com.grad.ventora.documentation.repository;
import com.grad.ventora.documentation.entity.DocumentationPageContent; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface DocumentationPageContentRepository extends JpaRepository<DocumentationPageContent,Long>{ Optional<DocumentationPageContent> findFirstByOrderByIdAsc(); }
