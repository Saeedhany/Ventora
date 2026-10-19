package com.grad.ventora.contact.service.impl;

import com.grad.ventora.contact.service.IContactService;
import com.grad.ventora.dto.ContactRequest;
import com.grad.ventora.dto.ContactResponse;
import com.grad.ventora.entity.ContactMessage;
import com.grad.ventora.repository.ContactMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements IContactService {

    private final ContactMessageRepository contactMessageRepository;


    @Override
    public ContactResponse sendMessage(ContactRequest request) {

        contactMessageRepository.save(contactRequestToEntity(request));

        return new ContactResponse(
                "Your message has been sent successfully"
        );
    }



    private ContactMessage contactRequestToEntity(ContactRequest request){
        ContactMessage contactMessage = new ContactMessage();

        contactMessage.setFullName(request.fullName());
        contactMessage.setEmail(request.email());
        contactMessage.setSubject(request.subject());
        contactMessage.setMessage(request.message());

        return contactMessage;
    }
}
