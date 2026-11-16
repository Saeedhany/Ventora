package com.grad.ventora.integrations.pagecontent.service.impl;

import com.grad.ventora.integrations.pagecontent.dto.IntegrationPageContentRequest;
import com.grad.ventora.integrations.pagecontent.dto.IntegrationPageContentResponse;
import com.grad.ventora.integrations.pagecontent.entity.IntegrationPageContent;
import com.grad.ventora.integrations.pagecontent.repository.IntegrationPageContentRepository;
import com.grad.ventora.integrations.pagecontent.service.IIntegrationPageContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
public class IntegrationPageContentServiceImpl implements IIntegrationPageContentService {
    private final IntegrationPageContentRepository repository;

    @Override
    @Transactional(readOnly = true)
    public IntegrationPageContentResponse getPublic() {
        return repository.findFirstByOrderByIdAsc().map(this::toResponse).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public IntegrationPageContentResponse getAdmin() {
        return repository.findFirstByOrderByIdAsc().map(this::toResponse).orElse(null);
    }

    @Override
    public IntegrationPageContentResponse save(IntegrationPageContentRequest request) {
        IntegrationPageContent content = repository.findFirstByOrderByIdAsc()
                .orElseGet(IntegrationPageContent::new);

        content.setHeroTitle(request.heroTitle());
        content.setHeroDescription(request.heroDescription());
        content.setHeroImageUrl(request.heroImageUrl());
        content.setHeroButtonLabel(request.heroButtonLabel());
        content.setHeroButtonUrl(request.heroButtonUrl());

        content.setPopularTitle(request.popularTitle());
        content.setPopularDescription(request.popularDescription());

        content.setEasySetupTitle(request.easySetupTitle());
        content.setEasySetupDescription(request.easySetupDescription());
        content.setEasySetupPoints(request.easySetupPoints() == null
                ? new ArrayList<>() : new ArrayList<>(request.easySetupPoints()));
        content.setEasySetupButtonLabel(request.easySetupButtonLabel());
        content.setEasySetupButtonUrl(request.easySetupButtonUrl());

        content.setCategoriesTitle(request.categoriesTitle());
        content.setCategoriesDescription(request.categoriesDescription());

        content.setBottomCtaTitle(request.bottomCtaTitle());
        content.setBottomCtaDescription(request.bottomCtaDescription());
        content.setBottomCtaButtonLabel(request.bottomCtaButtonLabel());
        content.setBottomCtaButtonUrl(request.bottomCtaButtonUrl());

        return toResponse(repository.save(content));
    }

    private IntegrationPageContentResponse toResponse(IntegrationPageContent x) {
        return new IntegrationPageContentResponse(
                x.getId(), x.getHeroTitle(), x.getHeroDescription(), x.getHeroImageUrl(),
                x.getHeroButtonLabel(), x.getHeroButtonUrl(), x.getPopularTitle(),
                x.getPopularDescription(), x.getEasySetupTitle(), x.getEasySetupDescription(),
                x.getEasySetupPoints(), x.getEasySetupButtonLabel(), x.getEasySetupButtonUrl(),
                x.getCategoriesTitle(), x.getCategoriesDescription(), x.getBottomCtaTitle(),
                x.getBottomCtaDescription(), x.getBottomCtaButtonLabel(), x.getBottomCtaButtonUrl(),
                x.getCreatedAt(), x.getUpdatedAt()
        );
    }
}
