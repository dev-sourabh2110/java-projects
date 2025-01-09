package com.data.controller;

import com.data.pojo.MakeOfferRequest;
import com.data.service.MakeOfferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/offer")
public class MakeOfferController {

    @Autowired
    private MakeOfferService makeOfferService;

    @Secured("USER")
    @PostMapping("/request")
    public ResponseEntity<String> makeOffer(@Valid @RequestBody MakeOfferRequest makeOfferRequest) {
        // Save the offer and return a message
        String message = makeOfferService.saveOffer(makeOfferRequest);
        return ResponseEntity.ok(message);
    }
}
