package com.data.service;

import com.data.entity.CarEntity;
import com.data.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarFilterService {

    private final CarRepository carRepository;

    public CarFilterService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarEntity> searchCars(String title, String model, String make) {
        return carRepository.findByTitleContainingIgnoreCaseOrModelContainingIgnoreCaseOrMakeContainingIgnoreCase(
                title, model, make);
    }

    public List<CarEntity> filterByCategoryAndPrice(String type, String make, Double minPrice, Double maxPrice) {
        return carRepository.findByTypeAndMakeAndRegularPriceBetween(type, make, minPrice, maxPrice);
    }

//    public Double calculateRevenue() {
//        return carRepository.sumCarRevenue();
//    }

//    public List<CarEntity> getPendingApprovalCars() {
//        return carRepository.findByIsApproved(false);
//    }
}


