package com.data.service;

import com.data.entity.*;
import com.data.pojo.response.CarBasicDTO;
import com.data.pojo.response.CarDTO;
import com.data.pojo.response.CarMediaDTO;
import com.data.pojo.response.CarSearchDTO;
import com.data.repository.CarMediaRepository;
import com.data.repository.CarRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final CarMediaRepository carMediaRepository;

    public CarService(CarRepository carRepository, CarMediaRepository carMediaRepository) {
        this.carRepository = carRepository;
        this.carMediaRepository = carMediaRepository;
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

    public CarMediaEntity saveCarMedia(Long carId, MultipartFile photo1,
                                       MultipartFile photo2,
                                       MultipartFile photo3,
                                       MultipartFile photo4,
                                       MultipartFile photo5,
                                       String videoUrl, MultipartFile vinReport) {
        Optional<CarEntity> carEntity = carRepository.findById(carId);
        if (carEntity.isEmpty()) {
            return null;
            //return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found.");
        }

        CarMediaEntity carMedia = new CarMediaEntity();
        carMedia.setCar(carEntity.get());

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

            return carMediaRepository.save(carMedia);
            //return media;
        } catch (IOException e) {
            return null;
            // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save media files.");
        }
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

    public List<CarSearchDTO> searchCars(String keyword) {
        List<CarEntity> cars = carRepository.searchByKeyword(keyword);
        List<CarSearchDTO> searchResults = new ArrayList<>();

        for (CarEntity car : cars) {
            // Fetch all photos for the car
            Optional<CarMediaEntity> carMedia = carMediaRepository.findByCarId(car.getId());
            List<String> photoUrls = new ArrayList<>();

            carMedia.ifPresent(media -> {
                if (media.getPhoto1() != null) photoUrls.add("/api/cars/media/" + car.getId() + "/photo1");
                if (media.getPhoto2() != null) photoUrls.add("/api/cars/media/" + car.getId() + "/photo2");
                if (media.getPhoto3() != null) photoUrls.add("/api/cars/media/" + car.getId() + "/photo3");
                if (media.getPhoto4() != null) photoUrls.add("/api/cars/media/" + car.getId() + "/photo4");
                if (media.getPhoto5() != null) photoUrls.add("/api/cars/media/" + car.getId() + "/photo5");
            });

            // Create DTO and add to results
            searchResults.add(new CarSearchDTO(
                    car.getId(),
                    car.getTitle(),
                    car.getMake(),
                    car.getModel(),
                    car.getType(),
                    car.getRegularPrice(),
                    photoUrls
            ));
        }

        return searchResults;
    }

    private List<CarBasicDTO> getCarBasicDTOList(List<CarEntity> cars) {
        return cars.stream().map(car -> {
            // Map basic fields
            CarBasicDTO dto = new CarBasicDTO();
            dto.setId(car.getId());
            dto.setTitle(car.getTitle());
            dto.setMake(car.getMake());
            dto.setModel(car.getModel());
            dto.setType(car.getType());
            dto.setYear(car.getYear());
            dto.setCondition(car.getCondition());
            dto.setStockNumber(car.getStockNumber());
            dto.setVinNumber(car.getVinNumber());
            dto.setRegularPrice(car.getRegularPrice());
            dto.setSalePrice(car.getSalePrice());
            dto.setRequestPrice(car.getRequestPrice());
            dto.setDescription(car.getDescription());
            dto.setPriceLabel(car.isPriceLabel());
            dto.setCreateTime(car.getCreateTime());
            dto.setUpdateTime(car.getUpdateTime());

            // Map media from CarMediaEntity if present
            CarMediaDTO mediaDTO = null;
            CarMediaEntity media = car.getMedia();
            if (media != null) {
                mediaDTO = new CarMediaDTO();
                // We return URL endpoints for photos rather than raw data.
                // Adjust these endpoints if needed.
                mediaDTO.setPhoto1(media.getPhoto1() != null ? "/api/cars/media/" + car.getId() + "/photo1" : null);
                mediaDTO.setPhoto2(media.getPhoto2() != null ? "/api/cars/media/" + car.getId() + "/photo2" : null);
                mediaDTO.setPhoto3(media.getPhoto3() != null ? "/api/cars/media/" + car.getId() + "/photo3" : null);
                mediaDTO.setPhoto4(media.getPhoto4() != null ? "/api/cars/media/" + car.getId() + "/photo4" : null);
                mediaDTO.setPhoto5(media.getPhoto5() != null ? "/api/cars/media/" + car.getId() + "/photo5" : null);
                mediaDTO.setVideoUrl(media.getVideoUrl());
                mediaDTO.setVinReport(media.getVinReport() != null ? "/api/cars/media/" + car.getId() + "/vinReport" : null);
            }
            dto.setMedia(mediaDTO);

            return dto;
        }).collect(Collectors.toList());
    }

    // 1. Get cars by type (limited to 6) ordered by createTime descending
// Get 6 cars by type and return basic details with media
    public List<CarBasicDTO> getCarsByTypeBasic(String type) {
        List<CarEntity> cars = carRepository.findTop6ByTypeOrderByCreateTimeDesc(type);
        return getCarBasicDTOList(cars);
    }

    // 2. Get trending cars based on wishlist counts (limited to 6)
    public List<CarBasicDTO> getTrendingCars() {
        List<CarEntity> trendingCars = carRepository.findTrendingCars(PageRequest.of(0, 6));
        return getCarBasicDTOList(trendingCars);
    }

    // 3. Get cars by brand (make) (limited to 6) ordered by createTime descending
    public List<CarBasicDTO> getCarsByMake(String make) {
        List<CarEntity> top6ByMakeOrderByCreateTimeDesc = carRepository.findTop6ByMakeOrderByCreateTimeDesc(make);
        return getCarBasicDTOList(top6ByMakeOrderByCreateTimeDesc);
    }

    // 4. Get recently added cars (limited to 6) ordered by createTime descending
    public List<CarBasicDTO> getRecentlyAddedCars() {
        List<CarEntity> top6ByOrderByCreateTimeDesc = carRepository.findTop6ByOrderByCreateTimeDesc();
        return getCarBasicDTOList(top6ByOrderByCreateTimeDesc);
    }

    // 5. Get all cars (limited to 6) – for a general “all cars” category
    public List<CarBasicDTO> getAllCarsLimited() {
        List<CarEntity> top6ByOrderByCreateTimeDesc = carRepository.findTop6ByOrderByCreateTimeDesc();
        return getCarBasicDTOList(top6ByOrderByCreateTimeDesc);
    }
}


