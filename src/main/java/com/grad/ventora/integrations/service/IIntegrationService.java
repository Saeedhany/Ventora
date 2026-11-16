package com.grad.ventora.integrations.service;
import com.grad.ventora.integrations.dto.*; import java.util.*;
public interface IIntegrationService {
 List<IntegrationResponse> getPublic(String category);
 IntegrationResponse getPublicBySlug(String slug);
 List<IntegrationResponse> getAdmin(); IntegrationResponse create(IntegrationRequest request);
 IntegrationResponse update(Long id,IntegrationRequest request); void delete(Long id);
}
