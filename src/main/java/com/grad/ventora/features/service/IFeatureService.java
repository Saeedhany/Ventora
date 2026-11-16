package com.grad.ventora.features.service;
import com.grad.ventora.features.dto.*;
import java.util.List;
public interface IFeatureService {
    List<FeatureResponse> getActiveFeatures();
    FeatureResponse getActiveFeature(String identifier);
    List<FeatureResponse> getAllFeaturesForAdmin();
    FeatureResponse createFeature(FeatureRequest request);
    FeatureResponse updateFeature(Long id, FeatureRequest request);
    void deleteFeature(Long id);
    void reorderFeatures(FeatureReorderRequest request);
}
