package com.grad.ventora.help.controller;
import com.grad.ventora.help.dto.*;
import com.grad.ventora.help.service.HelpCenterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/help")
@RequiredArgsConstructor
public class HelpCenterController {
    private final HelpCenterService service;

    @GetMapping("/categories")
    public List<HelpCategoryResponse> categories(){ return service.categories(false); }
    @GetMapping("/categories/admin")
    public List<HelpCategoryResponse> adminCategories(){ return service.categories(true); }
    @PostMapping("/categories/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public HelpCategoryResponse createCategory(@Valid @RequestBody HelpCategoryRequest r){ return service.saveCategory(null,r); }
    @PutMapping("/categories/{id}/admin")
    public HelpCategoryResponse updateCategory(@PathVariable Long id,@Valid @RequestBody HelpCategoryRequest r){ return service.saveCategory(id,r); }
    @DeleteMapping("/categories/{id}/admin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id){ service.deleteCategory(id); }

    @GetMapping("/faqs")
    public Page<HelpFaqResponse> faqs(@RequestParam(required=false) String search,
        @RequestParam(required=false) String category,@RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int size){ return service.listFaqs(search,category,page,size); }
    @GetMapping("/faqs/featured")
    public List<HelpFaqResponse> featuredFaqs(){ return service.featuredFaqs(); }
    @GetMapping("/faqs/{id}")
    public HelpFaqResponse faq(@PathVariable Long id){ return service.getFaq(id); }
    @GetMapping("/faqs/admin")
    public Page<HelpFaqResponse> adminFaqs(@RequestParam(required=false) String search,
        @RequestParam(required=false) String category,@RequestParam(defaultValue="0") int page,
        @RequestParam(defaultValue="10") int size){ return service.adminFaqs(search,category,page,size); }
    @PostMapping("/faqs/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public HelpFaqResponse createFaq(@Valid @RequestBody HelpFaqRequest r){ return service.createFaq(r); }
    @PutMapping("/faqs/{id}/admin")
    public HelpFaqResponse updateFaq(@PathVariable Long id,@Valid @RequestBody HelpFaqRequest r){ return service.updateFaq(id,r); }
    @PatchMapping("/faqs/{id}/publish/admin")
    public HelpFaqResponse publishFaq(@PathVariable Long id){ return service.publishFaq(id); }
    @PatchMapping("/faqs/{id}/unpublish/admin")
    public HelpFaqResponse unpublishFaq(@PathVariable Long id){ return service.unpublishFaq(id); }
    @DeleteMapping("/faqs/{id}/admin")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFaq(@PathVariable Long id){ service.deleteFaq(id); }

    @GetMapping("/page-content")
    public HelpCenterPageContentResponse pageContent(){ return service.pageContent(); }
    @GetMapping("/page-content/admin")
    public HelpCenterPageContentResponse adminPageContent(){ return service.adminPageContent(); }
    @PutMapping("/page-content/admin")
    public HelpCenterPageContentResponse savePageContent(@RequestBody HelpCenterPageContentRequest r){ return service.savePageContent(r); }
}
