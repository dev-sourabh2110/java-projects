package com.data.service;

import com.data.dto.OfferPriceDTO;
import com.data.dto.UserDTO;
import com.data.entity.CarEntity;
import com.data.entity.MakeOfferEntity;
import com.data.entity.UserEntity;
import com.data.repository.CarRepository;
import com.data.repository.MakeOfferRepository;
import com.data.pojo.MakeOfferRequest;
import com.data.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MakeOfferService {

    @Autowired
    private MakeOfferRepository makeOfferRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CarRepository carRepository;

    public String saveOffer(@Valid MakeOfferRequest offerRequest) {
        // Fetch the logged-in user
        String loggedInEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByEmail(loggedInEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Fetch the car
        CarEntity carEntity = carRepository.findById(offerRequest.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        // Check if the email already exists
        Optional<MakeOfferEntity> existingOffer = makeOfferRepository.findById(1L);
        if (existingOffer.isPresent()) {
            return "Offer with this email already exists";
        }

        // Save new offer
        MakeOfferEntity offerEntity = new MakeOfferEntity();
        offerEntity.setUser(userEntity);
        offerEntity.setCar(carEntity);
        offerEntity.setDrivingLicenseNumber(offerRequest.getDrivingLicenseNumber());
        offerEntity.setOfferedPrice(offerRequest.getOfferedPrice());
        offerEntity.setFinalOfferedPrice(offerRequest.getFinalOfferedPrice());

        makeOfferRepository.save(offerEntity);

        return "Offer saved successfully!";
    }

    public List<OfferPriceDTO> getOfferPricesByStatus(String status) {
        return makeOfferRepository.findByStatus(status)
                .stream()
                .map(offer -> {
                    UserEntity user = offer.getUser();
                    UserDTO userDTO = new UserDTO(
                            user.getId(), user.getFirstName(), user.getLastName(),
                            user.getEmail(), user.getPhoneNumber(), user.isEnabled(),
                            user.getCreateTime(), user.getUpdateTime()
                    );

                    return new OfferPriceDTO(
                            offer.getId(),
                            userDTO,
                            offer.getCar().getId(),
                            offer.getOfferedPrice(),
                            offer.getStatus(),
                            offer.getCreateTime()
                    );
                })
                .collect(Collectors.toList());
    }
}
