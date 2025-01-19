package com.data.service;

import com.data.entity.UserEntity;
import com.data.entity.WishlistEntity;
import com.data.repository.CarRepository;
import com.data.repository.UserRepository;
import com.data.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    public String addToWishlist(String userEmail, Long carId) {
        var user = userRepository.findById(userEmail)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        var car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));

        WishlistEntity wishlist = new WishlistEntity();
        wishlist.setUser(user);
        wishlist.setCar(car);

        wishlistRepository.save(wishlist);
        return "Car added to wishlist successfully.";
    }

    public List<WishlistEntity> getWishlist(String userEmail) {
        return wishlistRepository.findByUserEmail(userEmail);
    }
}
