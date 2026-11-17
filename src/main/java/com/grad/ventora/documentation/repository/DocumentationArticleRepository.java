package com.grad.ventora.documentation.repository;
import com.grad.ventora.documentation.entity.*; import org.springframework.data.domain.*; import org.springframework.data.jpa.repository.JpaRepository; import java.util.*;
public interface DocumentationArticleRepository extends JpaRepository<DocumentationArticle,Long>{
 boolean existsByVersion_IdAndSlug(Long versionId,String slug); boolean existsByVersion_IdAndSlugAndIdNot(Long versionId,String slug,Long id);
 boolean existsByCategory_Id(Long id); boolean existsByVersion_Id(Long id); long countByCategory_Id(Long id); List<DocumentationArticle> findAllByVersion_Slug(String versionSlug);
 Page<DocumentationArticle> findByVersion_SlugAndStatusAndCategory_SlugAndCategory_ActiveTrue(String version,DocumentationArticleStatus status,String category,Pageable pageable);
 Page<DocumentationArticle> findByVersion_SlugAndStatusAndCategory_ActiveTrue(String version,DocumentationArticleStatus status,Pageable pageable);
 Page<DocumentationArticle> findByVersion_SlugAndStatusAndCategory_ActiveTrueAndTitleContainingIgnoreCaseOrVersion_SlugAndStatusAndCategory_ActiveTrueAndDescriptionContainingIgnoreCase(String v1,DocumentationArticleStatus s1,String q1,String v2,DocumentationArticleStatus s2,String q2,Pageable p);
 Optional<DocumentationArticle> findByVersion_SlugAndSlugAndStatusAndCategory_ActiveTrue(String version,String slug,DocumentationArticleStatus status);
 List<DocumentationArticle> findAllByVersion_IdAndCategory_IdAndStatusOrderByDisplayOrderAsc(Long versionId,Long categoryId,DocumentationArticleStatus status);
 List<DocumentationArticle> findAllByVersion_IdAndCategory_IdOrderByDisplayOrderAsc(Long versionId,Long categoryId);
 Page<DocumentationArticle> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title,String description,Pageable pageable);
}
