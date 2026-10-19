package com.grad.ventora.contact.service;

import com.grad.ventora.dto.ContactRequest;
import com.grad.ventora.dto.ContactResponse;

public interface IContactService {

    ContactResponse sendMessage(ContactRequest request);

}
