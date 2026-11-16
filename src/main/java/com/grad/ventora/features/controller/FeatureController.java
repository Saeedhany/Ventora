package com.grad.ventora.features.controller;

import com.grad.ventora.features.dto.*;
import com.grad.ventora.features.service.IFeatureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequiredArgsConstructor @RequestMapping("/features")
public class FeatureController {
    private final IFeatureService featureService;

    // Public website endpoints
    @GetMapping
    public ResponseEntity<List<FeatureResponse>> getActiveFeatures() {
        return ResponseEntity.ok(featureService.getActiveFeatures());
    }
    @GetMapping("/{id}")
    public ResponseEntity<FeatureResponse> getActiveFeature(@PathVariable String id) {
        return ResponseEntity.ok(featureService.getActiveFeature(id));
    }

    // Website administration endpoints
    @GetMapping("/admin")
    public ResponseEntity<List<FeatureResponse>> getAllFeaturesForAdmin() {
        return ResponseEntity.ok(featureService.getAllFeaturesForAdmin());
    }
    @PostMapping("/admin")
    public ResponseEntity<FeatureResponse> createFeature(@Valid @RequestBody FeatureRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(featureService.createFeature(request));
    }
    @PutMapping("/{id}/admin")
    public ResponseEntity<FeatureResponse> updateFeature(@PathVariable Long id, @Valid @RequestBody FeatureRequest request) {
        return ResponseEntity.ok(featureService.updateFeature(id, request));
    }
    @DeleteMapping("/{id}/admin")
    public ResponseEntity<Void> deleteFeature(@PathVariable Long id) {
        featureService.deleteFeature(id); return ResponseEntity.noContent().build();
    }
    @PatchMapping("/admin/reorder")
    public ResponseEntity<Void> reorderFeatures(@Valid @RequestBody FeatureReorderRequest request) {
        featureService.reorderFeatures(request); return ResponseEntity.noContent().build();
    }
}
