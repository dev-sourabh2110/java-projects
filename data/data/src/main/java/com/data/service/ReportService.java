package com.data.service;

import com.data.dto.AdminReportDTO;
import com.data.repository.CarRepository;
import com.data.repository.MakeOfferRepository;
import com.data.repository.TestDriveRepository;
import com.data.repository.VendorRepository;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    private final CarRepository carRepository;
    private final MakeOfferRepository offerRepository;
    private final TestDriveRepository testDriveRepository;
    private final VendorRepository vendorRepository;

    public ReportService(CarRepository carRepository, MakeOfferRepository offerRepository, TestDriveRepository testDriveRepository, VendorRepository vendorRepository) {
        this.carRepository = carRepository;
        this.offerRepository = offerRepository;
        this.testDriveRepository = testDriveRepository;
        this.vendorRepository = vendorRepository;
    }

    public AdminReportDTO getReports() {
        double totalRevenue = carRepository.getTotalRevenue() != null ? carRepository.getTotalRevenue() : 0.0;
        long totalOffers = offerRepository.getTotalOffers();
        long totalTestDrives = testDriveRepository.getTotalTestDrives();
        String mostSoldCar = carRepository.getMostSoldCar();
        String mostActiveVendor = vendorRepository.getMostActiveVendor();

        return new AdminReportDTO(totalRevenue, totalOffers, totalTestDrives, mostSoldCar, mostActiveVendor);
    }
}
