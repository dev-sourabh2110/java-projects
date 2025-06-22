package com.data.controller;

import com.data.entity.WishlistEntity;
import com.data.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    @Secured("USER")
    @PostMapping("/add/{userEmail}/{carId}")
    public ResponseEntity<String> addToWishlist(@PathVariable String userEmail, @PathVariable Long carId) {
        String message = wishlistService.addToWishlist(userEmail, carId);
        return ResponseEntity.ok(message);
    }

    //@Secured("USER")
    @GetMapping("/{userEmail}")
    public ResponseEntity<List<WishlistEntity>> getWishlist(@PathVariable String userEmail) {
        return ResponseEntity.ok(wishlistService.getWishlist(userEmail));
    }
}


