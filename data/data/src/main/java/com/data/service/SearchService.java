package com.data.service;

import com.data.entity.CarEntity;
import com.data.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    @Autowired
    private CarRepository carRepository;

    public List<CarEntity> searchCars(String query) {
        return carRepository.findByTitleContainingIgnoreCaseOrModelContainingIgnoreCaseOrMakeContainingIgnoreCase(
                query, query, query);
    }
}

