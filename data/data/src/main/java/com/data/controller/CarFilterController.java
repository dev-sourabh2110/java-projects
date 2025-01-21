package com.data.controller;

import com.data.entity.CarEntity;
import com.data.service.CarFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarFilterController {
    @Autowired
    private CarFilterService carFilterService;

    @GetMapping("/filter")
    public ResponseEntity<List<CarEntity>> filterCars(
            @RequestParam String category,
            @RequestParam String brand,
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        return ResponseEntity.ok(carFilterService.filterByCategoryAndPrice(category, brand, minPrice, maxPrice));
    }
}

