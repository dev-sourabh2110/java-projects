package com.data.controller;

import com.data.entity.WishlistEntity;
import com.data.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<List<WishlistEntity>> getWishlist(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(wishlistService.getWishlist(userDetails.getUsername()));
    }

    @PostMapping
    public ResponseEntity<String> addToWishlist(@AuthenticationPrincipal UserDetails user, @RequestBody WishlistEntity wishlistEntity) {
        return ResponseEntity.ok(wishlistService.addToWishlist(user.getUsername(), wishlistEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeFromWishlist(@PathVariable Long id) {
        return ResponseEntity.ok(wishlistService.removeFromWishlist(id));
    }
}

