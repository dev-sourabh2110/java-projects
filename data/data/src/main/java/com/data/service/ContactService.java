package com.data.service;

import com.data.entity.ContactEntity;
import com.data.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public String saveContact(ContactEntity contact) {
        contactRepository.save(contact);
        return "Contact form submitted successfully.";
    }
}
