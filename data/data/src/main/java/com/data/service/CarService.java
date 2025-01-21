package com.data.service;

import com.data.entity.*;
import com.data.pojo.response.CarDTO;
import com.data.repository.CarPhotoRepository;
import com.data.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public CarEntity saveBasicCarDetails(CarEntity car) {
        return carRepository.save(car);
    }

    public CarSpecificationsEntity saveCarSpecifications(Long carId, CarSpecificationsEntity specifications) {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        specifications.setCar(car);
        car.setSpecifications(specifications);
        carRepository.save(car);
        return specifications;
    }

    public CarFeaturesEntity saveCarFeatures(Long carId, CarFeaturesEntity features) {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        features.setCar(car);
        car.setFeatures(features);
        carRepository.save(car);
        return features;
    }

    public CarMediaEntity saveCarMedia(Long carId, CarMediaEntity media) {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        media.setCar(car);
        car.setMedia(media);
        carRepository.save(car);
        return media;
    }

    public CarAddressEntity saveCarAddress(Long carId, CarAddressEntity address) {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        address.setCar(car);
        car.setAddress(address);
        carRepository.save(car);
        return address;
    }

    public List<CarEntity> getCarsByVendor(Long vendorId) {
        return carRepository.findByVendorId(vendorId);
    }

    public List<CarEntity> getSimilarProducts(String category, String brand) {
        return carRepository.findByTypeAndModel(category, brand);
    }

    public CarEntity getCarDetails(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));
    }

    public List<CarDTO> getAllCarsBasicDetails() {
        return carRepository.findAllBasicDetails();
    }
}


