package com.grad.ventora.homepage.service;
import com.grad.ventora.homepage.dto.*;
import com.grad.ventora.homepage.entity.HomePageLocale;
import java.util.List;
public interface IHomePageService {
 HomePageContentResponse getPublished(HomePageLocale locale);
 HomePageContentResponse createDraft(HomePageDraftRequest request);
 HomePageContentResponse getDraft(Long draftId);
 HomePageSectionResponse addSection(Long draftId, HomePageSectionRequest request);
 HomePageSectionContentResponse getSectionContent(Long draftId, Long sectionId);
 HomePageSectionContentResponse updateSectionContent(Long draftId, Long sectionId, HomePageSectionContentRequest request);
 HomePageSectionResponse updateSection(Long draftId, Long sectionId, HomePageSectionRequest request);
 void deleteSection(Long draftId, Long sectionId);
 void reorderSections(Long draftId, HomePageReorderRequest request);
 HomePageContentResponse updateSeo(Long draftId, String seoTitle, String seoDescription);
 HomePageContentResponse publish(Long draftId, HomePagePublishRequest request);
 List<HomePageContentResponse> getVersions(HomePageLocale locale);
 List<HomePageHistoryResponse> getHistory(HomePageLocale locale);
 HomePageContentResponse restore(Long versionId, HomePagePublishRequest request);
}
