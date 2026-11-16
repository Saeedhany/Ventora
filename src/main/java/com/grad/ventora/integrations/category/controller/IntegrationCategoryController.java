package com.grad.ventora.integrations.category.controller;
import com.grad.ventora.integrations.category.dto.*;import com.grad.ventora.integrations.category.service.ICategoryService;import jakarta.validation.Valid;import lombok.RequiredArgsConstructor;import org.springframework.http.*;import org.springframework.web.bind.annotation.*;import java.util.*;
@RestController @RequiredArgsConstructor @RequestMapping("/integration-categories")
public class IntegrationCategoryController {
 private final ICategoryService service;
 @GetMapping public ResponseEntity<List<CategoryResponse>> getPublic(){return ResponseEntity.ok(service.getPublic());}
 @GetMapping("/admin") public ResponseEntity<List<CategoryResponse>> getAdmin(){return ResponseEntity.ok(service.getAdmin());}
 @PostMapping("/admin") public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest r){return ResponseEntity.status(HttpStatus.CREATED).body(service.create(r));}
 @PutMapping("/{id}/admin") public ResponseEntity<CategoryResponse> update(@PathVariable Long id,@Valid @RequestBody CategoryRequest r){return ResponseEntity.ok(service.update(id,r));}
 @DeleteMapping("/{id}/admin") public ResponseEntity<Void> delete(@PathVariable Long id){service.delete(id);return ResponseEntity.noContent().build();}
}
