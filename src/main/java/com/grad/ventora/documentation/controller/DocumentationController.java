package com.grad.ventora.documentation.controller;
import com.grad.ventora.documentation.dto.*; import com.grad.ventora.documentation.service.DocumentationService;
import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.data.domain.Page; import org.springframework.http.HttpStatus; import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/documentation") @RequiredArgsConstructor
public class DocumentationController {
 private final DocumentationService service;
 @GetMapping("/versions") public List<DocumentationVersionResponse> versions(){return service.versions(false);}
 @GetMapping("/versions/admin") public List<DocumentationVersionResponse> adminVersions(){return service.versions(true);}
 @PostMapping("/versions/admin") @ResponseStatus(HttpStatus.CREATED) public DocumentationVersionResponse createVersion(@Valid @RequestBody DocumentationVersionRequest r){return service.saveVersion(null,r);}
 @PutMapping("/versions/{id}/admin") public DocumentationVersionResponse updateVersion(@PathVariable Long id,@Valid @RequestBody DocumentationVersionRequest r){return service.saveVersion(id,r);}
 @PatchMapping("/versions/{id}/latest/admin") public DocumentationVersionResponse latest(@PathVariable Long id){return service.setLatest(id);}
 @PatchMapping("/versions/{id}/activate/admin") public DocumentationVersionResponse activate(@PathVariable Long id){return service.setVersionActive(id,true);}
 @PatchMapping("/versions/{id}/deactivate/admin") public DocumentationVersionResponse deactivate(@PathVariable Long id){return service.setVersionActive(id,false);}
 @DeleteMapping("/versions/{id}/admin") @ResponseStatus(HttpStatus.NO_CONTENT) public void deleteVersion(@PathVariable Long id){service.deleteVersion(id);}
 @GetMapping("/categories") public List<DocumentationCategoryResponse> categories(){return service.categories(false);}
 @GetMapping("/categories/admin") public List<DocumentationCategoryResponse> adminCategories(){return service.categories(true);}
 @PostMapping("/categories/admin") @ResponseStatus(HttpStatus.CREATED) public DocumentationCategoryResponse createCategory(@Valid @RequestBody DocumentationCategoryRequest r){return service.saveCategory(null,r);}
 @PutMapping("/categories/{id}/admin") public DocumentationCategoryResponse updateCategory(@PathVariable Long id,@Valid @RequestBody DocumentationCategoryRequest r){return service.saveCategory(id,r);}
 @PatchMapping("/categories/{id}/activate/admin") public void activateCategory(@PathVariable Long id){service.setCategoryActive(id,true);}
 @PatchMapping("/categories/{id}/deactivate/admin") public void deactivateCategory(@PathVariable Long id){service.setCategoryActive(id,false);}
 @DeleteMapping("/categories/{id}/admin") @ResponseStatus(HttpStatus.NO_CONTENT) public void deleteCategory(@PathVariable Long id){service.deleteCategory(id);}
 @GetMapping("/articles") public Page<DocumentationArticleSummary> articles(@RequestParam(required=false) String version,@RequestParam(required=false) String category,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="10") int size){return service.articles(version,category,page,size);}
 @GetMapping("/articles/admin") public Page<DocumentationArticleResponse> adminArticles(@RequestParam(required=false) String search,@RequestParam(required=false) String version,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="10") int size){return service.adminArticles(search,version,page,size);}
 @GetMapping("/articles/{slug}") public DocumentationArticleResponse article(@PathVariable String slug,@RequestParam(required=false) String version){return service.article(version,slug);}
 @GetMapping("/articles/{id}/admin") public DocumentationArticleResponse adminArticle(@PathVariable Long id){return service.adminArticle(id);}
 @PostMapping("/articles/admin") @ResponseStatus(HttpStatus.CREATED) public DocumentationArticleResponse createArticle(@Valid @RequestBody DocumentationArticleRequest r){return service.saveArticle(null,r);}
 @PutMapping("/articles/{id}/admin") public DocumentationArticleResponse updateArticle(@PathVariable Long id,@Valid @RequestBody DocumentationArticleRequest r){return service.saveArticle(id,r);}
 @PatchMapping("/articles/{id}/publish/admin") public DocumentationArticleResponse publish(@PathVariable Long id){return service.publish(id);}
 @PatchMapping("/articles/{id}/unpublish/admin") public DocumentationArticleResponse unpublish(@PathVariable Long id){return service.unpublish(id);}
 @DeleteMapping("/articles/{id}/admin") @ResponseStatus(HttpStatus.NO_CONTENT) public void deleteArticle(@PathVariable Long id){service.deleteArticle(id);}
 @GetMapping("/search") public Page<DocumentationArticleSummary> search(@RequestParam String query,@RequestParam(required=false) String version,@RequestParam(defaultValue="0") int page,@RequestParam(defaultValue="10") int size){return service.search(query,version,page,size);}
 @GetMapping public DocumentationPageContentResponse home(){return service.pageContent();}
 @GetMapping("/page-content") public DocumentationPageContentResponse pageContent(){return service.pageContent();}
 @GetMapping("/page-content/admin") public DocumentationPageContentResponse adminPageContent(){return service.pageContent();}
 @PutMapping("/page-content/admin") public DocumentationPageContentResponse savePageContent(@Valid @RequestBody DocumentationPageContentRequest r){return service.savePageContent(r);}
}
