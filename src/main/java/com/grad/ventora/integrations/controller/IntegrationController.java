package com.grad.ventora.integrations.controller;
import com.grad.ventora.integrations.dto.*;import com.grad.ventora.integrations.service.IIntegrationService;import jakarta.validation.Valid;import lombok.RequiredArgsConstructor;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequiredArgsConstructor @RequestMapping("/integrations")
public class IntegrationController {
 private final IIntegrationService service;
 @GetMapping public ResponseEntity<List<IntegrationResponse>> getPublic(@RequestParam(required=false) String category){return ResponseEntity.ok(service.getPublic(category));}
 @GetMapping("/{slug}") public ResponseEntity<IntegrationResponse> getBySlug(@PathVariable String slug){return ResponseEntity.ok(service.getPublicBySlug(slug));}
 @GetMapping("/admin") public ResponseEntity<List<IntegrationResponse>> getAdmin(){return ResponseEntity.ok(service.getAdmin());}
 @PostMapping("/admin") public ResponseEntity<IntegrationResponse> create(@Valid @RequestBody IntegrationRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r));}
 @PutMapping("/{id}/admin") public ResponseEntity<IntegrationResponse> update(@PathVariable Long id,@Valid @RequestBody IntegrationRequest r){return ResponseEntity.ok(service.update(id,r));}
 @DeleteMapping("/{id}/admin") public ResponseEntity<Void> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.noContent().build();}
}
