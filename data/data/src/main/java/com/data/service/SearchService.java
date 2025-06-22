package com.data.service;

import com.data.entity.CarEntity;
import com.data.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SearchService {
    @Autowired
    private CarRepository carRepository;

    public List<CarEntity> searchCars(String query) {
        if (query == null || query.trim().isEmpty()) {
            return Collections.emptyList();
        }

        try {
            return carRepository.findByTitleContainingIgnoreCaseOrModelContainingIgnoreCaseOrMakeContainingIgnoreCase(
                    query, query, query);
        } catch (Exception e) {
            throw new RuntimeException("Error searching for cars: " + e.getMessage(), e);
        }
    }
}