package com.grad.ventora.features.service.impl;

import com.grad.ventora.exception.exceptions.ResourceNotFoundException;
import com.grad.ventora.features.dto.*;
import com.grad.ventora.features.entity.Feature;
import com.grad.ventora.features.entity.FeaturePoint;
import com.grad.ventora.features.entity.FeatureSection;
import com.grad.ventora.features.repository.FeatureRepository;
import com.grad.ventora.features.service.IFeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FeatureServiceImpl implements IFeatureService {
    private final FeatureRepository featureRepository;

    @Override
    @Transactional(readOnly = true)
    public List<FeatureResponse> getActiveFeatures() {
        return featureRepository.findByActiveTrueOrderByDisplayOrderAscIdAsc()
                .stream().map(feature -> toResponse(feature, false)).toList();
    }

    // Accepts either a numeric ID (backward compatibility) or a slug.
    @Override
    @Transactional(readOnly = true)
    public FeatureResponse getActiveFeature(String identifier) {
        Optional<Feature> feature;
        if (identifier.matches("\\d+")) {
            try {
                feature = featureRepository.findByIdAndActiveTrue(Long.parseLong(identifier));
            } catch (NumberFormatException ex) {
                feature = Optional.empty();
            }
        } else {
            feature = featureRepository.findBySlugAndActiveTrue(identifier);
        }
        return feature.map(value -> toResponse(value, false))
                .orElseThrow(() -> new ResourceNotFoundException("Active feature not found: " + identifier));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FeatureResponse> getAllFeaturesForAdmin() {
        return featureRepository.findAllByOrderByDisplayOrderAscIdAsc()
                .stream().map(feature -> toResponse(feature, true)).toList();
    }

    @Override
    public FeatureResponse createFeature(FeatureRequest request) {
        validateUniqueFields(request, null);
        Feature feature = new Feature();
        applyRequest(feature, request);
        return toResponse(featureRepository.save(feature), true);
    }

    @Override
    public FeatureResponse updateFeature(Long id, FeatureRequest request) {
        Feature feature = featureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feature not found: " + id));
        validateUniqueFields(request, id);
        applyRequest(feature, request);
        return toResponse(featureRepository.save(feature), true);
    }

    @Override
    public void deleteFeature(Long id) {
        Feature feature = featureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Feature not found: " + id));
        featureRepository.delete(feature);
    }

    @Override
    public void reorderFeatures(FeatureReorderRequest request) {
        List<Feature> features = featureRepository.findAll();
        Map<Long, Feature> byId = features.stream()
                .collect(Collectors.toMap(Feature::getId, Function.identity()));
        List<Long> ids = request.featureIds();
        if (ids.size() != features.size() || new HashSet<>(ids).size() != ids.size()
                || !byId.keySet().equals(new HashSet<>(ids))) {
            throw new IllegalArgumentException("featureIds must contain every feature exactly once");
        }
        for (int i = 0; i < ids.size(); i++) byId.get(ids.get(i)).setDisplayOrder(i);
        featureRepository.saveAll(features);
    }

    private void validateUniqueFields(FeatureRequest request, Long currentId) {
        boolean nameExists = currentId == null
                ? featureRepository.existsByNameIgnoreCase(request.name().trim())
                : featureRepository.existsByNameIgnoreCaseAndIdNot(request.name().trim(), currentId);
        boolean slugExists = currentId == null
                ? featureRepository.existsBySlug(request.slug())
                : featureRepository.existsBySlugAndIdNot(request.slug(), currentId);
        if (nameExists) throw new IllegalArgumentException("A feature with this name already exists");
        if (slugExists) throw new IllegalArgumentException("A feature with this slug already exists");
    }

    private void applyRequest(Feature feature, FeatureRequest request) {
        feature.setName(request.name().trim());
        feature.setSlug(request.slug());
        feature.setSectionLabel(request.sectionLabel().trim());
        feature.setTitle(request.title().trim());
        feature.setDescription(request.description().trim());
        feature.setImageUrl(request.imageUrl());
        feature.setCtaLabel(request.ctaLabel());
        feature.setCtaUrl(request.ctaUrl());
        feature.setDisplayOrder(request.displayOrder() == null ? 0 : request.displayOrder());
        feature.setActive(request.active() == null || request.active());

        // PUT is a full replacement for points and sections when their arrays are supplied.
        if (request.points() != null) {
            feature.clearPoints();
            for (FeaturePointRequest pointRequest : request.points()) {
                FeaturePoint point = new FeaturePoint();
                point.setContent(pointRequest.content().trim());
                point.setDisplayOrder(pointRequest.displayOrder() == null ? 0 : pointRequest.displayOrder());
                feature.addPoint(point);
            }
        }
        if (request.sections() != null) {
            feature.clearSections();
            for (FeatureSectionRequest sectionRequest : request.sections()) {
                FeatureSection section = new FeatureSection();
                section.setType(sectionRequest.type().trim().toUpperCase(Locale.ROOT));
                section.setTitle(sectionRequest.title());
                section.setSubtitle(sectionRequest.subtitle());
                section.setDescription(sectionRequest.description());
                section.setImageUrl(sectionRequest.imageUrl());
                section.setButtonLabel(sectionRequest.buttonLabel());
                section.setButtonUrl(sectionRequest.buttonUrl());
                section.setContent(sectionRequest.content());
                section.setDisplayOrder(sectionRequest.displayOrder() == null ? 0 : sectionRequest.displayOrder());
                section.setActive(sectionRequest.active() == null || sectionRequest.active());
                feature.addSection(section);
            }
        }
    }

    private FeatureResponse toResponse(Feature feature, boolean includeInactiveSections) {
        List<FeatureResponse.FeaturePointResponse> points = feature.getPoints().stream()
                .map(point -> new FeatureResponse.FeaturePointResponse(
                        point.getId(), point.getContent(), point.getDisplayOrder())).toList();
        List<FeatureResponse.FeatureSectionResponse> sections = feature.getSections().stream()
                .filter(section -> includeInactiveSections || section.isActive())
                .map(section -> new FeatureResponse.FeatureSectionResponse(
                        section.getId(), section.getType(), section.getTitle(), section.getSubtitle(),
                        section.getDescription(), section.getImageUrl(), section.getButtonLabel(),
                        section.getButtonUrl(), section.getContent(), section.getDisplayOrder(), section.isActive()))
                .toList();
        return new FeatureResponse(feature.getId(), feature.getName(), feature.getSlug(),
                feature.getSectionLabel(), feature.getTitle(), feature.getDescription(), feature.getImageUrl(),
                feature.getCtaLabel(), feature.getCtaUrl(), feature.getDisplayOrder(), feature.isActive(),
                points, feature.getCreatedAt(), feature.getUpdatedAt(), sections);
    }
}
