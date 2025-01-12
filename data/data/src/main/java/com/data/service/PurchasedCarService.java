package com.data.service;

import com.data.entity.PurchasedCarEntity;
import com.data.repository.PurchasedCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchasedCarService {

    @Autowired
    private PurchasedCarRepository purchasedCarRepository;

    public List<PurchasedCarEntity> getPurchasedCars(String email) {
        return purchasedCarRepository.findByUserEmail(email);
    }
}

