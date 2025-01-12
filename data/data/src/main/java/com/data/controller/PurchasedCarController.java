package com.data.controller;

import com.data.entity.PurchasedCarEntity;
import com.data.service.PurchasedCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/purchased-cars")
public class PurchasedCarController {

    @Autowired
    private PurchasedCarService purchasedCarService;

    @GetMapping
    public ResponseEntity<List<PurchasedCarEntity>> getPurchasedCars(@AuthenticationPrincipal String email) {
        return ResponseEntity.ok(purchasedCarService.getPurchasedCars(email));
    }
}

