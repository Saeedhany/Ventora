package com.grad.ventora.about.controller;
import com.grad.ventora.about.dto.*; import com.grad.ventora.about.service.IAboutPageService; import jakarta.validation.Valid; import lombok.RequiredArgsConstructor; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*;
@RestController @RequiredArgsConstructor @RequestMapping("/about")
public class AboutPageController {
 private final IAboutPageService service;
 @GetMapping public ResponseEntity<AboutPageResponse> getPublished(){return ResponseEntity.ok(service.getPublished());}
 @GetMapping("/admin") public ResponseEntity<AboutPageResponse> getDraft(){return ResponseEntity.ok(service.getDraft());}
 @PutMapping("/admin") public ResponseEntity<AboutPageResponse> save(@Valid @RequestBody AboutPageRequest request){return ResponseEntity.ok(service.save(request));}
}
