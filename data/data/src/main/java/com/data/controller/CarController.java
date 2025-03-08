package com.data.controller;

import com.data.dto.CarResponseDTO;
import com.data.entity.*;
import com.data.pojo.response.CarBasicDTO;
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
    public ResponseEntity<Map<String, Object>> addBasicCarDetails(@RequestBody CarEntity car, @RequestParam Long vendorID) {
        // Fetch the logged-in vendor
    //    String loggedInEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        VendorEntity vendor = vendorRepository.findByUserId(vendorID)
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

        return carService.saveCarMedia(carId, photo1,
                photo2,
                photo3,
                photo4,
                photo5,videoUrl,vinReport);

    }


    @PostMapping("/{carId}/add-address")
    public ResponseEntity<String> addCarAddress(
            @PathVariable Long carId,
            @RequestBody CarAddressEntity address) {
        carService.saveCarAddress(carId, address);
        return ResponseEntity.ok("Car address saved.");
    }

    @GetMapping("/vendor")
    public ResponseEntity<List<CarResponseDTO>> getCarsByVendor(@RequestParam Long vendorId) {
        List<CarResponseDTO> cars = carService.getCarsByVendor(vendorId);
        return ResponseEntity.ok(cars);
    }

  //  @Secured({"USER", "VENDOR"})
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

    // 1. By-type column – fetch 6 cars by type (e.g., "SUV", "Compact")
    // GET endpoint for "by-type" home page (6 records, basic details + media)
//    @GetMapping("/by-type")
//    public ResponseEntity<List<CarBasicDTO>> getCarsByType(@RequestParam String type) {
//        List<CarBasicDTO> cars = carService.getCarsByTypeBasic(type);
//        return ResponseEntity.ok(cars);
//    }

    // 2. Trending – fetch 6 trending cars (based on wishlist count)
    @GetMapping("/trending")
    public ResponseEntity<List<CarBasicDTO>> getTrendingCars(@RequestParam int page,
                                                             @RequestParam int size) {
        List<CarBasicDTO> cars = carService.getTrendingCars(page, size);
        return ResponseEntity.ok(cars);
    }

//    // 3. By-brands (make) – fetch 6 cars by brand
//    @GetMapping("/by-make")
//    public ResponseEntity<List<CarBasicDTO>> getCarsByMake(@RequestParam String make) {
//        List<CarBasicDTO> cars = carService.getCarsByMake(make);
//        return ResponseEntity.ok(cars);
//    }

    // 4. Recently – fetch 6 most recently added cars (using createTime)
    @GetMapping("/recently")
    public ResponseEntity<List<CarBasicDTO>> getRecentlyAddedCars(@RequestParam int page,
                                                                  @RequestParam int size) {
        List<CarBasicDTO> cars = carService.getRecentlyAddedCars(page, size);
        return ResponseEntity.ok(cars);
    }

    // 5. All Cars – fetch 6 cars for the "all cars" category
    @GetMapping("/all")
    public ResponseEntity<List<CarBasicDTO>> getAllCarsLimited(@RequestParam int page,
                                                               @RequestParam int size) {
        List<CarBasicDTO> cars = carService.getAllCarsLimited(page, size);
        return ResponseEntity.ok(cars);
    }
//
//    // Endpoint for "by-type" with pagination
//    @GetMapping("/type")
//    public ResponseEntity<List<CarBasicDTO>> getCarsByType(
//            @RequestParam String type,
//            @RequestParam int page,
//            @RequestParam int size) {
//        List<CarBasicDTO> cars = carService.getCarsByType(type, page, size);
//        return ResponseEntity.ok(cars);
//    }

    @GetMapping("/searchCars")
    public ResponseEntity<List<CarBasicDTO>> searchCars(
            @RequestParam String filterField,
            @RequestParam String filterValue,
            @RequestParam int page,
            @RequestParam int size) {
        List<CarBasicDTO> cars = carService.searchCars(filterField, filterValue, page, size);
        return ResponseEntity.ok(cars);
    }

}


