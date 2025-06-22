
package com.data.controller;

import com.data.dto.car.*;
import com.data.entity.*;
import com.data.pojo.response.CarBasicDTO;
import com.data.repository.*;
import com.data.service.CarService;
import com.data.util.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    public CarController(CarService carService, VendorRepository vendorRepository,
                         CarSpecificationsRepository carSpecificationsRepository,
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

    /**
     * Add basic car details
     */
    @PostMapping("/add-basic")
    public ResponseEntity<Map<String, Object>> addBasicCarDetails(
            @RequestBody CarCreateDTO carCreateDTO,
            @RequestParam Long vendorID) {
        try {
            VendorEntity vendor = vendorRepository.findById(vendorID)
                    .orElseThrow(() -> new RuntimeException("Vendor not found"));

            CarEntity savedCar = carService.saveBasicCarDetails(carCreateDTO, vendor);
            return ApiResponse.success(Map.of("carId", savedCar.getId()), "Basic car details saved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Add car specifications
     */
    @PostMapping("/{carId}/add-specifications")
    public ResponseEntity<Map<String, Object>> addCarSpecifications(
            @PathVariable Long carId,
            @RequestBody CarSpecificationsEntity specifications) {
        try {
            carService.saveCarSpecifications(carId, specifications);
            return ApiResponse.success("Car specifications saved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Add car features
     */
    @PostMapping("/{carId}/add-features")
    public ResponseEntity<Map<String, Object>> addCarFeatures(
            @PathVariable Long carId,
            @RequestBody CarFeaturesEntity features) {
        try {
            carService.saveCarFeatures(carId, features);
            return ApiResponse.success("Car features saved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Add car media files
     */
    @PostMapping("/{carId}/add-media")
    public ResponseEntity<Map<String, Object>> addCarMedia(
            @PathVariable Long carId,
            @RequestParam(required = false) MultipartFile photo1,
            @RequestParam(required = false) MultipartFile photo2,
            @RequestParam(required = false) MultipartFile photo3,
            @RequestParam(required = false) MultipartFile photo4,
            @RequestParam(required = false) MultipartFile photo5,
            @RequestParam(required = false) String videoUrl,
            @RequestParam(required = false) MultipartFile vinReport) {
        try {
            Map<String, Object> result = carService.saveCarMedia(carId, photo1, photo2, photo3, photo4, photo5, videoUrl, vinReport);
            boolean isSuccess = Boolean.TRUE.equals(result.get("success"));
            if (isSuccess) {
                return ResponseEntity.ok(result);
            } else {
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Add car address
     */
    @PostMapping("/{carId}/add-address")
    public ResponseEntity<Map<String, Object>> addCarAddress(
            @PathVariable Long carId,
            @RequestBody CarAddressEntity address) {
        try {
            carService.saveCarAddress(carId, address);
            return ApiResponse.success("Car address saved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get all cars by vendor
     */
    @GetMapping("/vendor")
    public ResponseEntity<Map<String, Object>> getCarsByVendor(@RequestParam Long vendorId) {
        try {
            List<CarStatusDTO> cars = carService.getCarsByVendor(vendorId);
            return ApiResponse.success(cars, "Cars retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get similar cars
     */
   @GetMapping("/{carId}/similar")
    public ResponseEntity<Map<String, Object>> getSimilarProducts(@PathVariable Long carId) {
        try {
            List<CarSearchResultDTO> similarCars = carService.getSimilarProducts(carId);
            return ApiResponse.success(similarCars, "Similar cars retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get all cars
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllCars() {
        try {
            List<CarDetailsDTO> cars = carService.getAllCarsBasicDetails();
            return ApiResponse.success(cars, "All cars retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get car specifications
     */
    @GetMapping("/{carId}/specifications")
    public ResponseEntity<Map<String, Object>> getCarSpecifications(@PathVariable Long carId) {
        try {
            Optional<CarSpecificationsEntity> specifications = carSpecificationsRepository.findByCarId(carId);
            if (specifications.isPresent()) {
                return ApiResponse.success(specifications.get(), "Car specifications retrieved successfully");
            } else {
                return ApiResponse.notFound("Car specifications not found for car ID: " + carId);
            }
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get car features
     */
    @GetMapping("/{carId}/features")
    public ResponseEntity<Map<String, Object>> getCarFeatures(@PathVariable Long carId) {
        try {
            Optional<CarFeaturesEntity> features = carFeaturesRepository.findByCarId(carId);
            if (features.isPresent()) {
                return ApiResponse.success(features.get(), "Car features retrieved successfully");
            } else {
                return ApiResponse.notFound("Car features not found for car ID: " + carId);
            }
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get car media
     */
   @GetMapping("/{carId}/media")
    public ResponseEntity<Map<String, Object>> getCarMedia(@PathVariable Long carId) {
        try {
            Optional<CarMediaEntity> media = carMediaRepository.findByCarId(carId);
            if (media.isPresent()) {
                return ApiResponse.success(media.get(), "Car media retrieved successfully");
            } else {
                return ApiResponse.notFound("Car media not found for car ID: " + carId);
            }
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get car address
     */
    @GetMapping("/{carId}/address")
    public ResponseEntity<Map<String, Object>> getCarAddress(@PathVariable Long carId) {
        try {
            Optional<CarAddressEntity> address = carAddressRepository.findByCarId(carId);
            if (address.isPresent()) {
                return ApiResponse.success(address.get(), "Car address retrieved successfully");
            } else {
                return ApiResponse.notFound("Car address not found for car ID: " + carId);
            }
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Search cars by keyword
     */
    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchCars(@RequestParam String keyword) {
        try {
            List<CarSearchResultDTO> searchResults = carService.searchCars(keyword);
            return ApiResponse.success(searchResults, "Search results retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get car photo
     */
    @GetMapping("/media/{carId}/photo{photoIndex}")
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long carId, @PathVariable int photoIndex) {
        try {
            Optional<CarMediaEntity> media = carMediaRepository.findByCarId(carId);
            byte[] photo = null;

            if (media.isPresent()) {
                switch (photoIndex) {
                    case 1:
                        photo = media.get().getPhoto1();
                        break;
                    case 2:
                        photo = media.get().getPhoto2();
                        break;
                    case 3:
                        photo = media.get().getPhoto3();
                        break;
                    case 4:
                        photo = media.get().getPhoto4();
                        break;
                    case 5:
                        photo = media.get().getPhoto5();
                        break;
                }
            }

            if (photo != null) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body(photo);
            }

            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Get trending cars
     */
    @GetMapping("/trending")
    public ResponseEntity<Map<String, Object>> getTrendingCars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<CarSearchResultDTO> cars = carService.getTrendingCars(page, size);
            return ApiResponse.success(cars, "Trending cars retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get recently added cars
     */
    @GetMapping("/recently")
    public ResponseEntity<Map<String, Object>> getRecentlyAddedCars(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<CarSearchResultDTO> cars = carService.getRecentlyAddedCars(page, size);
            return ApiResponse.success(cars, "Recently added cars retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get all cars with pagination
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllCarsLimited(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<CarSearchResultDTO> cars = carService.getAllCarsLimited(page, size);
            return ApiResponse.success(cars, "Cars retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Search cars by filter
     */
    @GetMapping("/searchCars")
    public ResponseEntity<Map<String, Object>> searchCarsByFilter(
            @RequestParam String filterField,
            @RequestParam String filterValue,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        try {
            List<CarSearchResultDTO> cars = carService.searchCars(filterField, filterValue, page, size);
            return ApiResponse.success(cars, "Search results retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get cars by status (for admin/vendor)
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<Map<String, Object>> getCarsByStatus(@PathVariable String status) {
        try {
            List<CarStatusDTO> cars = carService.getCarsByStatus(status);
            return ApiResponse.success(cars, "Cars with status '" + status + "' retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get car detail by ID
     */
    @GetMapping("/{carId}")
    public ResponseEntity<Map<String, Object>> getCarById(@PathVariable Long carId) {
        try {
            CarDetailsDTO car = carService.getCarDetails(carId);
            return ApiResponse.success(car, "Car details retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Approve a car listing (admin only)
     */
    @Secured("ROLE_ADMIN")
    @PutMapping("/{carId}/approve")
    public ResponseEntity<Map<String, Object>> approveCar(@PathVariable Long carId) {
        try {
            CarStatusDTO car = carService.approveCar(carId);
            return ApiResponse.success(car, "Car listing approved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Reject a car listing (admin only)
     */
    @Secured("ROLE_ADMIN")
    @PutMapping("/{carId}/reject")
    public ResponseEntity<Map<String, Object>> rejectCar(@PathVariable Long carId) {
        try {
            CarStatusDTO car = carService.rejectCar(carId);
            return ApiResponse.success(car, "Car listing rejected successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Mark a car as sold
     */
    @PutMapping("/{carId}/sold")
    public ResponseEntity<Map<String, Object>> markCarAsSold(@PathVariable Long carId) {
        try {
            CarStatusDTO car = carService.markCarAsSold(carId);
            return ApiResponse.success(car, "Car marked as sold successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Delete a car listing
     */
    @DeleteMapping("/{carId}")
    public ResponseEntity<Map<String, Object>> deleteCar(@PathVariable Long carId) {
        try {
            carService.deleteCar(carId);
            return ApiResponse.success("Car deleted successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get VIN report
     */
    @GetMapping("/media/{carId}/vinReport")
    public ResponseEntity<byte[]> getVinReport(@PathVariable Long carId) {
        try {
            Optional<CarMediaEntity> media = carMediaRepository.findByCarId(carId);

            if (media.isPresent() && media.get().getVinReport() != null) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(media.get().getVinReport());
            }

            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Get cars by make
     */
    @GetMapping("/make/{make}")
    public ResponseEntity<Map<String, Object>> getCarsByMake(
            @PathVariable String make,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        try {
            List<CarSearchResultDTO> cars = carService.getCarsByMake(make, page, size);
            return ApiResponse.success(cars, "Cars by make '" + make + "' retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get cars by type
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<Map<String, Object>> getCarsByType(
            @PathVariable String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {
        try {
            List<CarSearchResultDTO> cars = carService.getCarsByType(type, page, size);
            return ApiResponse.success(cars, "Cars by type '" + type + "' retrieved successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update car status
     */
    @PutMapping("/{carId}/status")
    public ResponseEntity<Map<String, Object>> updateCarStatus(
            @PathVariable Long carId,
            @RequestParam String status) {
        try {
            CarStatusDTO updatedCar = carService.updateCarStatus(carId, status);
            return ApiResponse.success(updatedCar, "Car status updated successfully");
        } catch (Exception e) {
            return ApiResponse.exception(e, HttpStatus.BAD_REQUEST);
        }

    }
}