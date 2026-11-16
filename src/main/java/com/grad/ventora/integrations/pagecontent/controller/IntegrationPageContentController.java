package com.grad.ventora.integrations.pagecontent.controller;

import com.grad.ventora.integrations.pagecontent.dto.IntegrationPageContentRequest;
import com.grad.ventora.integrations.pagecontent.dto.IntegrationPageContentResponse;
import com.grad.ventora.integrations.pagecontent.service.IIntegrationPageContentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/integrations/page-content")
public class IntegrationPageContentController {
    private final IIntegrationPageContentService service;

    @GetMapping
    public ResponseEntity<IntegrationPageContentResponse> getPublic() {
        IntegrationPageContentResponse response = service.getPublic();
        return response == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(response);
    }

    @GetMapping("/admin")
    public ResponseEntity<IntegrationPageContentResponse> getAdmin() {
        IntegrationPageContentResponse response = service.getAdmin();
        return response == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(response);
    }

    @PutMapping("/admin")
    public ResponseEntity<IntegrationPageContentResponse> save(
            @Valid @RequestBody IntegrationPageContentRequest request) {
        return ResponseEntity.ok(service.save(request));
    }
}
