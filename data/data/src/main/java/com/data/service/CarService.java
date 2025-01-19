package com.data.service;

import com.data.entity.CarEntity;
import com.data.entity.CarPhotoEntity;
import com.data.entity.VendorEntity;
import com.data.repository.CarPhotoRepository;
import com.data.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarPhotoRepository carPhotoRepository;

    public CarService(CarRepository carRepository, CarPhotoRepository carPhotoRepository) {
        this.carRepository = carRepository;
        this.carPhotoRepository = carPhotoRepository;
    }

    public CarEntity getCarDetails(Long carId) {
        return carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));
    }

    public List<CarEntity> getSimilarProducts(String category, String brand) {
        return carRepository.findByCategoryAndBrand(category, brand);
    }

    public CarEntity addCar(CarEntity car, List<byte[]> photos, VendorEntity vendor) {
        if (vendor == null) {
            throw new IllegalArgumentException("Vendor cannot be null");
        }
        car.setVendor(vendor);
        car.setApproved(false); // Requires admin approval
        car.setSold(false);

        CarEntity savedCar = carRepository.save(car);

        if (photos != null && !photos.isEmpty()) {
            for (byte[] photo : photos) {
                CarPhotoEntity carPhoto = new CarPhotoEntity();
                carPhoto.setCar(savedCar);
                carPhoto.setPhoto(photo);
                carPhotoRepository.save(carPhoto);
            }
        }

        return savedCar;
    }

    public List<CarEntity> getCarsByVendor(Long vendorId) {
        return carRepository.findByVendorId(vendorId);
    }

    public void updateCarPhotos(Long carId, List<byte[]> photos) {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        // Remove existing photos
        carPhotoRepository.deleteAll(car.getPhotos());

        // Add new photos
        for (byte[] photo : photos) {
            CarPhotoEntity carPhoto = new CarPhotoEntity();
            carPhoto.setCar(car);
            carPhoto.setPhoto(photo);
            carPhotoRepository.save(carPhoto);
        }
    }
}

