package com.grad.ventora.integrations.pagecontent.service;

import com.grad.ventora.integrations.pagecontent.dto.IntegrationPageContentRequest;
import com.grad.ventora.integrations.pagecontent.dto.IntegrationPageContentResponse;

public interface IIntegrationPageContentService {
    IntegrationPageContentResponse getPublic();
    IntegrationPageContentResponse getAdmin();
    IntegrationPageContentResponse save(IntegrationPageContentRequest request);
}
