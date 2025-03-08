package com.data.controller;

import com.data.dto.OfferPriceDTO;
import com.data.service.MakeOfferService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/offer-prices")
public class AdminOfferPriceController {

    private final MakeOfferService offerPriceService;

    public AdminOfferPriceController(MakeOfferService offerPriceService) {
        this.offerPriceService = offerPriceService;
    }

    @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<OfferPriceDTO>> getOfferPrices(@RequestParam String status) {
        return ResponseEntity.ok(offerPriceService.getOfferPricesByStatus(status));
    }
}
