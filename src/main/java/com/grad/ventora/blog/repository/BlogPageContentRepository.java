package com.grad.ventora.blog.repository;
import com.grad.ventora.blog.entity.BlogPageContent; import org.springframework.data.jpa.repository.JpaRepository; import java.util.Optional;
public interface BlogPageContentRepository extends JpaRepository<BlogPageContent,Long> { Optional<BlogPageContent> findFirstByOrderByIdAsc(); }
