package com.data.service;

import com.data.repository.CarRepository;
import com.data.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminApprovalService {
    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private CarRepository carRepository;

    public String approveVendor(Long vendorId) {
        var vendor = vendorRepository.findById(vendorId)
                .orElseThrow(() -> new IllegalArgumentException("Vendor not found"));
        vendor.setEnabled(true);
        vendorRepository.save(vendor);
        return "Vendor approved successfully.";
    }

    public String approveCar(Long carId) {
        var car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));
        car.setApproved(true);
        carRepository.save(car);
        return "Car approved successfully.";
    }
}
