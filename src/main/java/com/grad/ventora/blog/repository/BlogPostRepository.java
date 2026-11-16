package com.grad.ventora.blog.repository;
import com.grad.ventora.blog.entity.*; import org.springframework.data.domain.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface BlogPostRepository extends JpaRepository<BlogPost,Long> {
 Optional<BlogPost> findBySlugAndStatus(String slug,BlogPostStatus status);
 Page<BlogPost> findByStatus(BlogPostStatus status,Pageable pageable);
 Page<BlogPost> findByStatusAndCategory_Slug(BlogPostStatus status,String slug,Pageable pageable);
 Page<BlogPost> findByStatusAndTitleContainingIgnoreCaseOrStatusAndExcerptContainingIgnoreCase(BlogPostStatus s1,String title,BlogPostStatus s2,String excerpt,Pageable pageable);
 Page<BlogPost> findByStatusAndCategory_SlugAndTitleContainingIgnoreCase(BlogPostStatus status,String category,String title,Pageable pageable);
 List<BlogPost> findTop5ByStatusAndFeaturedTrueOrderByPublishedAtDesc(BlogPostStatus status);
 boolean existsBySlug(String slug); long countByCategory_IdAndStatus(Long id,BlogPostStatus status);
}
