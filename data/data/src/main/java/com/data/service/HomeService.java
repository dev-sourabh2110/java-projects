package com.data.service;

import com.data.entity.CarEntity;
import com.data.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {
    @Autowired
    private CarRepository carRepository;

    public List<CarEntity> getTrendingCars() {
        return carRepository.findTop5ByOrderByIdDesc(); // Example: Trending cars are the most expensive
    }

    public List<CarEntity> getRecentlyAddedCars() {
        return carRepository.findTop5ByOrderByIdDesc(); // Recently added cars by ID
    }

    public List<String> getCategories() {
        return carRepository.findDistinctCategories();
    }

    public List<String> getBrands() {
        return carRepository.findDistinctBrands();
    }
}

