package com.grad.ventora.homepage.controller;
import com.grad.ventora.homepage.dto.*;
import com.grad.ventora.homepage.entity.HomePageLocale;
import com.grad.ventora.homepage.service.IHomePageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequiredArgsConstructor @RequestMapping("/home")
public class HomePageController {
 private final IHomePageService service;
 @GetMapping public ResponseEntity<HomePageContentResponse> getPublished(@RequestParam(defaultValue="EN") HomePageLocale locale){return ResponseEntity.ok(service.getPublished(locale));}
 @PostMapping("/drafts/admin") public ResponseEntity<HomePageContentResponse> createDraft(@Valid @RequestBody HomePageDraftRequest request){return ResponseEntity.status(HttpStatus.CREATED).body(service.createDraft(request));}
 @GetMapping("/drafts/{draftId}/admin") public ResponseEntity<HomePageContentResponse> getDraft(@PathVariable Long draftId){return ResponseEntity.ok(service.getDraft(draftId));}
 @PutMapping("/drafts/{draftId}/seo/admin") public ResponseEntity<HomePageContentResponse> updateSeo(@PathVariable Long draftId,@RequestParam(required=false) String seoTitle,@RequestParam(required=false) String seoDescription){return ResponseEntity.ok(service.updateSeo(draftId,seoTitle,seoDescription));}
 @PostMapping("/drafts/{draftId}/sections/admin") public ResponseEntity<HomePageSectionResponse> addSection(@PathVariable Long draftId,@Valid @RequestBody HomePageSectionRequest request){return ResponseEntity.status(HttpStatus.CREATED).body(service.addSection(draftId,request));}
 @GetMapping("/drafts/{draftId}/sections/{sectionId}/content/admin") public ResponseEntity<HomePageSectionContentResponse> getSectionContent(@PathVariable Long draftId,@PathVariable Long sectionId){return ResponseEntity.ok(service.getSectionContent(draftId,sectionId));}
 @PutMapping("/drafts/{draftId}/sections/{sectionId}/content/admin") public ResponseEntity<HomePageSectionContentResponse> updateSectionContent(@PathVariable Long draftId,@PathVariable Long sectionId,@Valid @RequestBody HomePageSectionContentRequest request){return ResponseEntity.ok(service.updateSectionContent(draftId,sectionId,request));}
 @PutMapping("/drafts/{draftId}/sections/{sectionId}/admin") public ResponseEntity<HomePageSectionResponse> updateSection(@PathVariable Long draftId,@PathVariable Long sectionId,@Valid @RequestBody HomePageSectionRequest request){return ResponseEntity.ok(service.updateSection(draftId,sectionId,request));}
 @DeleteMapping("/drafts/{draftId}/sections/{sectionId}/admin") public ResponseEntity<Void> deleteSection(@PathVariable Long draftId,@PathVariable Long sectionId){service.deleteSection(draftId,sectionId);return ResponseEntity.noContent().build();}
 @PutMapping("/drafts/{draftId}/sections/reorder/admin") public ResponseEntity<Void> reorder(@PathVariable Long draftId,@Valid @RequestBody HomePageReorderRequest request){service.reorderSections(draftId,request);return ResponseEntity.noContent().build();}
 @PostMapping("/drafts/{draftId}/publish/admin") public ResponseEntity<HomePageContentResponse> publish(@PathVariable Long draftId,@RequestBody(required=false) HomePagePublishRequest request){return ResponseEntity.ok(service.publish(draftId,request==null?new HomePagePublishRequest(null,null):request));}
 @GetMapping("/versions/admin") public ResponseEntity<List<HomePageContentResponse>> versions(@RequestParam(defaultValue="EN") HomePageLocale locale){return ResponseEntity.ok(service.getVersions(locale));}
 @GetMapping("/history/admin") public ResponseEntity<List<HomePageHistoryResponse>> history(@RequestParam(defaultValue="EN") HomePageLocale locale){return ResponseEntity.ok(service.getHistory(locale));}
 @PostMapping("/versions/{versionId}/restore/admin") public ResponseEntity<HomePageContentResponse> restore(@PathVariable Long versionId,@RequestBody(required=false) HomePagePublishRequest request){return ResponseEntity.status(HttpStatus.CREATED).body(service.restore(versionId,request==null?new HomePagePublishRequest(null,null):request));}
}
