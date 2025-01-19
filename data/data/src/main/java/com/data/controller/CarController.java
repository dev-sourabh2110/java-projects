package com.data.controller;

import com.data.entity.CarEntity;
import com.data.entity.VendorEntity;
import com.data.repository.VendorRepository;
import com.data.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;
    private final VendorRepository vendorRepository;

    public CarController(CarService carService, VendorRepository vendorRepository) {
        this.carService = carService;
        this.vendorRepository = vendorRepository;
    }

    @Secured({"USER", "VENDOR"})
    @GetMapping("/{carId}")
    public ResponseEntity<CarEntity> getCarDetails(@PathVariable Long carId) {
        return ResponseEntity.ok(carService.getCarDetails(carId));
    }

    @Secured({"USER", "VENDOR"})
    @GetMapping("/{carId}/similar")
    public ResponseEntity<List<CarEntity>> getSimilarProducts(@PathVariable Long carId) {
        CarEntity car = carService.getCarDetails(carId);
        return ResponseEntity.ok(carService.getSimilarProducts(car.getCategory(), car.getBrand()));
    }

    @Secured("VENDOR")
    @PostMapping("/add")
    public ResponseEntity<String> addCar(
            @RequestParam String name,
            @RequestParam String model,
            @RequestParam String brand,
            @RequestParam String category,
            @RequestParam Double price,
            @RequestParam List<MultipartFile> photos) throws Exception {

        if (photos.size() > 10) {
            return ResponseEntity.badRequest().body("Maximum of 10 photos allowed.");
        }

        // Fetch the logged-in vendor
        String loggedInEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        VendorEntity vendor = vendorRepository.findByUserEmail(loggedInEmail)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        List<byte[]> photoData = new ArrayList<>();
        for (MultipartFile photo : photos) {
            photoData.add(photo.getBytes());
        }

        CarEntity car = new CarEntity();
        car.setName(name);
        car.setModel(model);
        car.setBrand(brand);
        car.setCategory(category);
        car.setPrice(price);

        carService.addCar(car, photoData, vendor);

        return ResponseEntity.ok("Car added successfully. Awaiting admin approval.");
    }

    @Secured("VENDOR")
    @GetMapping
    public ResponseEntity<List<CarEntity>> getVendorCars(@RequestParam Long vendorId) {
        List<CarEntity> cars = carService.getCarsByVendor(vendorId);
        return ResponseEntity.ok(cars);
    }

    @Secured("VENDOR")
    @PutMapping("/{carId}/photos")
    public ResponseEntity<String> updateCarPhotos(
            @PathVariable Long carId,
            @RequestParam List<MultipartFile> photos) throws Exception {

        if (photos.size() > 10) {
            return ResponseEntity.badRequest().body("Maximum of 10 photos allowed.");
        }

        List<byte[]> photoData = new ArrayList<>();
        for (MultipartFile photo : photos) {
            photoData.add(photo.getBytes());
        }

        carService.updateCarPhotos(carId, photoData);
        return ResponseEntity.ok("Car photos updated successfully.");
    }
}

