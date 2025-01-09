package com.data.service;

import com.data.entity.TestDrive;
import com.data.pojo.TestDriveRequest;
import com.data.entity.UserEntity;
import com.data.repository.TestDriveRepository;
import com.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
public class TestDriveService {

    @Autowired
    private TestDriveRepository testDriveRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public String saveTestDriveRequest(TestDriveRequest testDriveRequest, BindingResult result) {
        if (result.hasErrors()) {
            return "Invalid input data: " + result.getAllErrors();
        }

        // Validate if the user exists
        Optional<UserEntity> user = userRepository.findByEmail(testDriveRequest.getEmail());
        if (user.isEmpty()) {
            return "User not found with the provided email";
        }

        // Create a TestDrive entity from the request data
        TestDrive testDrive = new TestDrive();
        testDrive.setName(testDriveRequest.getName());
        testDrive.setEmail(testDriveRequest.getEmail());
        testDrive.setPhoneNumber(testDriveRequest.getPhoneNumber());
        testDrive.setAddress(testDriveRequest.getAddress());
        testDrive.setDrivingLicenseNumber(testDriveRequest.getDrivingLicenseNumber());

        // Save the test drive request to the database
        testDriveRepository.save(testDrive);
        return "Test drive request successfully saved";
    }
}
