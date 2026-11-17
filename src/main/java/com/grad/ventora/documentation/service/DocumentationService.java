package com.grad.ventora.documentation.service;
import com.grad.ventora.documentation.dto.*; import org.springframework.data.domain.Page; import java.util.*;
public interface DocumentationService {
 List<DocumentationVersionResponse> versions(boolean admin); DocumentationVersionResponse saveVersion(Long id,DocumentationVersionRequest r); DocumentationVersionResponse setLatest(Long id); DocumentationVersionResponse setVersionActive(Long id,boolean active); void deleteVersion(Long id);
 List<DocumentationCategoryResponse> categories(boolean admin); DocumentationCategoryResponse saveCategory(Long id,DocumentationCategoryRequest r); void setCategoryActive(Long id,boolean active); void deleteCategory(Long id);
 Page<DocumentationArticleSummary> articles(String version,String category,int page,int size); Page<DocumentationArticleResponse> adminArticles(String search,String version,int page,int size); DocumentationArticleResponse article(String version,String slug); DocumentationArticleResponse adminArticle(Long id); DocumentationArticleResponse saveArticle(Long id,DocumentationArticleRequest r); DocumentationArticleResponse publish(Long id); DocumentationArticleResponse unpublish(Long id); void deleteArticle(Long id); Page<DocumentationArticleSummary> search(String query,String version,int page,int size);
 DocumentationPageContentResponse pageContent(); DocumentationPageContentResponse savePageContent(DocumentationPageContentRequest r);
}
