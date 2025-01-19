package com.data.service;

import com.data.entity.CarEntity;
import com.data.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarFilterService {
    @Autowired
    private CarRepository carRepository;

    public List<CarEntity> filterCars(String category, String brand, Double minPrice, Double maxPrice) {
        return carRepository.findByCategoryAndBrandAndPriceBetween(category, brand, minPrice, maxPrice);
    }
}

