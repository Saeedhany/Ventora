package com.grad.ventora.contact.controller;


import com.grad.ventora.contact.service.IContactService;
import com.grad.ventora.dto.ContactRequest;
import com.grad.ventora.dto.ContactResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contacts")
public class ContactController {

    private final IContactService contactService;


    @PostMapping
    public ResponseEntity<ContactResponse> sendMessage(
            @Valid @RequestBody ContactRequest request) {

        ContactResponse response = contactService.sendMessage(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}
