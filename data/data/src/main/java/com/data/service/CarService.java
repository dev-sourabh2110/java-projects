package com.data.service;

import com.data.dto.car.*;
import com.data.entity.*;
import com.data.enums.CarStatus;
import com.data.mapper.CarMapper;
import com.data.repository.*;
import com.data.specification.CarSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarSpecificationsRepository carSpecificationsRepository;
    private final CarFeaturesRepository carFeaturesRepository;
    private final CarMediaRepository carMediaRepository;
    private final CarAddressRepository carAddressRepository;

    @Autowired
    public CarService(CarRepository carRepository,
                     CarSpecificationsRepository carSpecificationsRepository,
                     CarFeaturesRepository carFeaturesRepository,
                     CarMediaRepository carMediaRepository,
                     CarAddressRepository carAddressRepository) {
        this.carRepository = carRepository;
        this.carSpecificationsRepository = carSpecificationsRepository;
        this.carFeaturesRepository = carFeaturesRepository;
        this.carMediaRepository = carMediaRepository;
        this.carAddressRepository = carAddressRepository;
    }

    /**
     * Save basic car details
     */
    @Transactional
    public CarEntity saveBasicCarDetails(CarCreateDTO carCreateDTO, VendorEntity vendor) {
        CarEntity car = CarMapper.toEntity(carCreateDTO, vendor);
        try {
            return carRepository.save(car);
        } catch (TransactionSystemException | CannotAcquireLockException e) {
            // Log the error
            System.out.println("Transaction failed, retrying: " + e.getMessage());
            // Sleep for a brief period to allow locks to clear
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            // Try again
            return carRepository.save(car);
        }
    }

    /**
     * Save car specifications
     */
    @Transactional
    public CarSpecificationsEntity saveCarSpecifications(Long carId, CarSpecificationsEntity specifications) {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        specifications.setCar(car);
        car.setSpecifications(specifications);
        carRepository.save(car);
        return specifications;
    }

    /**
     * Save car features
     */
    @Transactional
    public CarFeaturesEntity saveCarFeatures(Long carId, CarFeaturesEntity features) {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        features.setCar(car);
        car.setFeatures(features);
        carRepository.save(car);
        return features;
    }

    /**
     * Save car media files
     */
    @Transactional
    public Map<String, Object> saveCarMedia(Long carId, MultipartFile photo1,
                                          MultipartFile photo2,
                                          MultipartFile photo3,
                                          MultipartFile photo4,
                                          MultipartFile photo5,
                                          String videoUrl,
                                          MultipartFile vinReport) {
        Map<String, Object> response = new HashMap<>();

        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> {
                    response.put("success", false);
                    response.put("message", "Car not found with ID: " + carId);
                    return new RuntimeException("Car not found");
                });

        CarMediaEntity carMedia = carMediaRepository.findByCarId(carId)
                .orElseGet(() -> {
                    CarMediaEntity newMedia = new CarMediaEntity();
                    newMedia.setCar(car);
                    return newMedia;
                });

        try {
            if (photo1 != null) {
                carMedia.setPhoto1(photo1.getBytes());
            }
            if (photo2 != null) {
                carMedia.setPhoto2(photo2.getBytes());
            }
            if (photo3 != null) {
                carMedia.setPhoto3(photo3.getBytes());
            }
            if (photo4 != null) {
                carMedia.setPhoto4(photo4.getBytes());
            }
            if (photo5 != null) {
                carMedia.setPhoto5(photo5.getBytes());
            }
            if (vinReport != null) {
                carMedia.setVinReport(vinReport.getBytes());
            }
            carMedia.setVideoUrl(videoUrl);

            carMediaRepository.save(carMedia);

            response.put("success", true);
            response.put("message", "Car media saved successfully");
            return response;
        } catch (IOException e) {
            response.put("success", false);
            response.put("message", "Failed to save media files: " + e.getMessage());
            return response;
        }
    }

    /**
     * Save car address
     */
    @Transactional
    public CarAddressEntity saveCarAddress(Long carId, CarAddressEntity address) {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        address.setCar(car);
        car.setAddress(address);
        carRepository.save(car);
        return address;
    }

    /**
     * Get all cars by vendor
     */
    @Transactional
    public List<CarStatusDTO> getCarsByVendor(Long vendorId) {
        return CarMapper.toStatusDTOList(carRepository.findByVendorId(vendorId));
    }

    /**
     * Get similar products to a specific car
     */
    @Transactional
    public List<CarSearchResultDTO> getSimilarProducts(Long carId) {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        List<CarEntity> similarCars = carRepository.findByTypeAndModelAndIdNot(
                car.getType(), car.getModel(), carId);

        return CarMapper.toSearchResultDTOList(similarCars);
    }

    /**
     * Get car details
     */
    @Transactional
    public CarDetailsDTO getCarDetails(Long carId) {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        return CarMapper.toDetailsDTO(car);
    }

    /**
     * Get all cars' basic details
     */
    @Transactional
    public List<CarDetailsDTO> getAllCarsBasicDetails() {
        return CarMapper.toDetailsDTOList(carRepository.findAll());
    }

    /**
     * Search cars by keyword
     */
    @Transactional
    public List<CarSearchResultDTO> searchCars(String keyword) {
        List<CarEntity> cars = carRepository.searchByKeyword(keyword);
        return CarMapper.toSearchResultDTOList(cars);
    }

    /**
     * Get trending cars
     */
    @Transactional
    public List<CarSearchResultDTO> getTrendingCars(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Page<CarEntity> carPage = carRepository.findAll(pageable);
        return CarMapper.toSearchResultDTOList(carPage.getContent());
    }

    /**
     * Get recently added cars
     */
    @Transactional
    public List<CarSearchResultDTO> getRecentlyAddedCars(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Page<CarEntity> carPage = carRepository.findAll(pageable);
        return CarMapper.toSearchResultDTOList(carPage.getContent());
    }

    /**
     * Get all cars with pagination
     */
    @Transactional
    public List<CarSearchResultDTO> getAllCarsLimited(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Page<CarEntity> carPage = carRepository.findAll(pageable);
        return CarMapper.toSearchResultDTOList(carPage.getContent());
    }

    /**
     * Search cars by filter criteria
     */
    @Transactional
    public List<CarSearchResultDTO> searchCars(String filterField, String filterValue, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        var spec = new CarSpecification(filterField, filterValue);
        Page<CarEntity> resultPage = carRepository.findAll(spec, pageable);
        return CarMapper.toSearchResultDTOList(resultPage.getContent());
    }

    /**
     * Get cars by status
     */
    @Transactional
    public List<CarStatusDTO> getCarsByStatus(String status) {
        CarStatus carStatus = CarStatus.safeValueOf(status);
        List<CarEntity> cars = carRepository.findByStatus(carStatus.name());
        return CarMapper.toStatusDTOList(cars);
    }

    /**
     * Approve a car listing
     */
    @Transactional
    public CarStatusDTO approveCar(Long carId) {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with ID: " + carId));

        car.setStatus(CarStatus.APPROVED.name());
        CarEntity savedCar = carRepository.save(car);
        return CarMapper.toStatusDTO(savedCar);
    }

    /**
     * Reject a car listing
     */
    @Transactional
    public CarStatusDTO rejectCar(Long carId) {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with ID: " + carId));

        car.setStatus(CarStatus.REJECTED.name());
        CarEntity savedCar = carRepository.save(car);
        return CarMapper.toStatusDTO(savedCar);
    }

    /**
     * Mark a car as sold
     */
    @Transactional
    public CarStatusDTO markCarAsSold(Long carId) {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with ID: " + carId));

        car.setStatus(CarStatus.SOLD.name());
        CarEntity savedCar = carRepository.save(car);
        return CarMapper.toStatusDTO(savedCar);
    }

    /**
     * Delete a car listing
     */
    @Transactional
    public void deleteCar(Long carId) {
        if (!carRepository.existsById(carId)) {
            throw new RuntimeException("Car not found with ID: " + carId);
        }
        carRepository.deleteById(carId);
    }

    /**
     * Get cars by make with pagination
     */
    @Transactional
    public List<CarSearchResultDTO> getCarsByMake(String make, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Page<CarEntity> carPage = carRepository.findByMake(make, pageable);
        return CarMapper.toSearchResultDTOList(carPage.getContent());
    }

    /**
     * Get cars by type with pagination
     */
    @Transactional
    public List<CarSearchResultDTO> getCarsByType(String type, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Page<CarEntity> carPage = carRepository.findByType(type, pageable);
        return CarMapper.toSearchResultDTOList(carPage.getContent());
    }

    /**
     * Update car status
     */
    @Transactional
    public CarStatusDTO updateCarStatus(Long carId, String status) {
        CarEntity car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found with ID: " + carId));

        try {
            CarStatus carStatus = CarStatus.fromString(status);
            car.setStatus(carStatus.name());
            CarEntity savedCar = carRepository.save(car);
            return CarMapper.toStatusDTO(savedCar);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid car status: " + status);
        }
    }
}