package com.data.service;

import com.data.entity.*;
import com.data.pojo.response.CarDTO;
import com.data.pojo.response.CarSearchDTO;
import com.data.repository.CarMediaRepository;
import com.data.repository.CarPhotoRepository;
import com.data.repository.CarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

          return   carMediaRepository.save(carMedia);
        //return media;
        } catch (IOException e) {
            return  null;
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
}


