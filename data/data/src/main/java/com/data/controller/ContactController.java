package com.data.controller;

import com.data.entity.ContactEntity;
import com.data.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<String> submitContact(@RequestBody ContactEntity contact) {
        String message = contactService.saveContact(contact);
        return ResponseEntity.ok(message);
    }
}

