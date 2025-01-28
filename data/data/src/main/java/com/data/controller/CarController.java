package com.data.controller;

import com.data.entity.*;
import com.data.pojo.response.CarDTO;
import com.data.pojo.response.CarSearchDTO;
import com.data.repository.*;
import com.data.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;
    private final VendorRepository vendorRepository;
    private final CarSpecificationsRepository carSpecificationsRepository;
    private final CarFeaturesRepository carFeaturesRepository;
    private final CarMediaRepository carMediaRepository;
    private final CarAddressRepository carAddressRepository;

    public CarController(CarService carService, VendorRepository vendorRepository, CarSpecificationsRepository carSpecificationsRepository,
                         CarFeaturesRepository carFeaturesRepository,
CarMediaRepository carMediaRepository,
    CarAddressRepository carAddressRepository) {
        this.carService = carService;
        this.vendorRepository = vendorRepository;
        this.carSpecificationsRepository = carSpecificationsRepository;
        this.carFeaturesRepository = carFeaturesRepository;
        this.carMediaRepository = carMediaRepository;
        this.carAddressRepository = carAddressRepository;
    }

    @PostMapping("/add-basic")
    public ResponseEntity<Map<String, Object>> addBasicCarDetails(@RequestBody CarEntity car) {
        // Fetch the logged-in vendor
        String loggedInEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        VendorEntity vendor = vendorRepository.findByUserEmail(loggedInEmail)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
        car.setVendor(vendor);
        CarEntity savedCar = carService.saveBasicCarDetails(car);
        return ResponseEntity.ok(Map.of("carId", savedCar.getId(), "message", "Basic car details saved."));
    }

    @PostMapping("/{carId}/add-specifications")
    public ResponseEntity<String> addCarSpecifications(
            @PathVariable Long carId,
            @RequestBody CarSpecificationsEntity specifications) {
        carService.saveCarSpecifications(carId, specifications);
        return ResponseEntity.ok("Car specifications saved.");
    }

    @PostMapping("/{carId}/add-features")
    public ResponseEntity<String> addCarFeatures(
            @PathVariable Long carId,
            @RequestBody CarFeaturesEntity features) {
        carService.saveCarFeatures(carId, features);
        return ResponseEntity.ok("Car features saved.");
    }

    @PostMapping("/{carId}/add-media")
    public ResponseEntity<String> addCarMedia(
            @PathVariable Long carId,
            @RequestParam(required = false) MultipartFile photo1,
            @RequestParam(required = false) MultipartFile photo2,
            @RequestParam(required = false) MultipartFile photo3,
            @RequestParam(required = false) MultipartFile photo4,
            @RequestParam(required = false) MultipartFile photo5,
            @RequestParam(required = false) String videoUrl,
            @RequestParam(required = false) MultipartFile vinReport) {

        carService.saveCarMedia(carId, photo1,
                photo2,
                photo3,
                photo4,
                photo5,videoUrl,vinReport);

            return ResponseEntity.ok("Car media saved successfully.");

    }


    @PostMapping("/{carId}/add-address")
    public ResponseEntity<String> addCarAddress(
            @PathVariable Long carId,
            @RequestBody CarAddressEntity address) {
        carService.saveCarAddress(carId, address);
        return ResponseEntity.ok("Car address saved.");
    }

//    @GetMapping
//    public ResponseEntity<List<CarEntity>> getCarsByVendor(@RequestParam Long vendorId) {
//        List<CarEntity> cars = carService.getCarsByVendor(vendorId);
//        return ResponseEntity.ok(cars);
//    }

    @Secured({"USER", "VENDOR"})
    @GetMapping("/{carId}/similar")
    public ResponseEntity<List<CarEntity>> getSimilarProducts(@PathVariable Long carId) {
        CarEntity car = carService.getCarDetails(carId);
        return ResponseEntity.ok(carService.getSimilarProducts(car.getType(), car.getModel()));
    }

    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars() {
        return ResponseEntity.ok(carService.getAllCarsBasicDetails());
    }

    @GetMapping("/{carId}/specifications")
    public ResponseEntity<CarSpecificationsEntity> getCarSpecifications(@PathVariable Long carId) {
        return carSpecificationsRepository.findByCarId(carId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{carId}/features")
    public ResponseEntity<CarFeaturesEntity> getCarFeatures(@PathVariable Long carId) {
        return carFeaturesRepository.findByCarId(carId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{carId}/media")
    public ResponseEntity<CarMediaEntity> getCarMedia(@PathVariable Long carId) {
        return carMediaRepository.findByCarId(carId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{carId}/address")
    public ResponseEntity<CarAddressEntity> getCarAddress(@PathVariable Long carId) {
        return carAddressRepository.findByCarId(carId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<CarSearchDTO>> searchCars(@RequestParam String keyword) {
        List<CarSearchDTO> searchResults = carService.searchCars(keyword);
        return ResponseEntity.ok(searchResults);
    }

    @GetMapping("/media/{carId}/photo{photoIndex}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long carId, @PathVariable int photoIndex) {
        Optional<CarMediaEntity> media = carMediaRepository.findByCarId(carId);
        byte[] photo = null;

        if (media.isPresent()) {
            switch (photoIndex) {
                case 1: photo = media.get().getPhoto1(); break;
                case 2: photo = media.get().getPhoto2(); break;
                case 3: photo = media.get().getPhoto3(); break;
                case 4: photo = media.get().getPhoto4(); break;
                case 5: photo = media.get().getPhoto5(); break;
            }
        }

        if (photo != null) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(photo);
        }

        return ResponseEntity.notFound().build();
    }

}


