package com.data.service;

import com.data.entity.UserEntity;
import com.data.entity.WishlistEntity;
import com.data.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    public List<WishlistEntity> getWishlist(String email) {
        return wishlistRepository.findByUserEmail(email);
    }

    public String addToWishlist(String email, WishlistEntity wishlistEntity) {
        wishlistEntity.setUser(new UserEntity(email)); // Assume email uniquely identifies user
        wishlistRepository.save(wishlistEntity);
        return "Car added to wishlist";
    }

    public String removeFromWishlist(Long wishlistId) {
        wishlistRepository.deleteById(wishlistId);
        return "Car removed from wishlist";
    }
}
