package com.grad.ventora.integrations.pagecontent.repository;

import com.grad.ventora.integrations.pagecontent.entity.IntegrationPageContent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IntegrationPageContentRepository extends JpaRepository<IntegrationPageContent, Long> {
    Optional<IntegrationPageContent> findFirstByOrderByIdAsc();
}
