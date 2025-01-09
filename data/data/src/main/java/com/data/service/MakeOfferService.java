package com.data.service;

import com.data.entity.MakeOfferEntity;
import com.data.repository.MakeOfferRepository;
import com.data.pojo.MakeOfferRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class MakeOfferService {

    @Autowired
    private MakeOfferRepository makeOfferRepository;

    public String saveOffer(@Valid MakeOfferRequest offerRequest) {
        // Check if the email already exists
        Optional<MakeOfferEntity> existingOffer = makeOfferRepository.findByEmail(offerRequest.getEmail());
        if (existingOffer.isPresent()) {
            return "Offer with this email already exists";
        }

        // Save new offer
        MakeOfferEntity offerEntity = new MakeOfferEntity();
        offerEntity.setName(offerRequest.getName());
        offerEntity.setEmail(offerRequest.getEmail());
        offerEntity.setPhoneNumber(offerRequest.getPhoneNumber());
        offerEntity.setAddress(offerRequest.getAddress());
        offerEntity.setDrivingLicenseNumber(offerRequest.getDrivingLicenseNumber());
        offerEntity.setOfferedPrice(offerRequest.getOfferedPrice());
        offerEntity.setFinalOfferedPrice(offerRequest.getFinalOfferedPrice());

        makeOfferRepository.save(offerEntity);

        return "Offer saved successfully!";
    }
}
