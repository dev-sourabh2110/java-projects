package com.data.service;

import com.data.entity.ContactEntity;
import com.data.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    public String saveContact(ContactEntity contact) {
        contactRepository.save(contact);
        return "Your message has been sent to the admin.";
    }
}
