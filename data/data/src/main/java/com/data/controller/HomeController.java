package com.data.controller;

import com.data.entity.CarEntity;
import com.data.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    //@Secured({"USER", "VENDOR"})
    @GetMapping("/trending-cars")
    public ResponseEntity<List<CarEntity>> getTrendingCars() {
        return ResponseEntity.ok(homeService.getTrendingCars());
    }

   // @Secured({"USER", "VENDOR"})
    @GetMapping("/recently-added")
    public ResponseEntity<List<CarEntity>> getRecentlyAddedCars() {
        return ResponseEntity.ok(homeService.getRecentlyAddedCars());
    }

   // @Secured("USER")
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getCategories() {
        return ResponseEntity.ok(homeService.getCategories());
    }

   // @Secured("USER")
    @GetMapping("/brands")
    public ResponseEntity<List<String>> getBrands() {
        return ResponseEntity.ok(homeService.getBrands());
    }
}

