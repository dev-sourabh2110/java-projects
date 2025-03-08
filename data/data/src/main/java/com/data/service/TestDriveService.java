package com.data.service;

import com.data.dto.TestDriveDTO;
import com.data.dto.UserDTO;
import com.data.entity.*;
import com.data.pojo.TestDriveRequest;
import com.data.repository.AppointmentRepository;
import com.data.repository.CarRepository;
import com.data.repository.TestDriveRepository;
import com.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestDriveService {

    @Autowired
    private TestDriveRepository testDriveRepository;

    @Autowired
    UserRepository  userRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    private AppointmentRepository appointmentRepository; // Inject AppointmentRepository

    @Transactional
    public String saveTestDriveRequest(TestDriveRequest testDriveRequest) {
        // Fetch the logged-in user
        String loggedInEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity userEntity = userRepository.findByEmail(loggedInEmail)
                .orElseThrow(() -> new RuntimeException("User not found"));


        // Fetch the car
        CarEntity carEntity = carRepository.findById(testDriveRequest.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        // Create a new TestDrive entity
        TestDriveEntity testDriveEntity = new TestDriveEntity();
        testDriveEntity.setUser(userEntity);
        testDriveEntity.setCar(carEntity);
        testDriveEntity.setDrivingLicenseNumber(testDriveRequest.getDrivingLicenseNumber());

        // Create a new Appointment entity
        Appointment appointment = new Appointment();
        appointment.setCarModel(testDriveRequest.getAppointmentRequest().getCarModel());
        appointment.setAppointmentDate(testDriveRequest.getAppointmentRequest().getAppointmentDate());
        appointment.setTestDrive(testDriveEntity);

        // Link Appointment with TestDrive
        //testDriveEntity.getAppointments().add(appointment);

        // Save TestDrive and Appointment
        testDriveRepository.save(testDriveEntity);
        appointmentRepository.save(appointment);

        return "Test drive and appointment saved successfully!";
    }

    public List<TestDriveDTO> getTestDrivesByStatus(String status) {
        return testDriveRepository.findByStatus(status)
                .stream()
                .map(testDrive -> {
                    UserEntity user = testDrive.getUser();
                    UserDTO userDTO = new UserDTO(
                            user.getId(), user.getFirstName(), user.getLastName(),
                            user.getEmail(), user.getPhoneNumber(), user.isEnabled(),
                            user.getCreateTime(), user.getUpdateTime()
                    );

                    return new TestDriveDTO(
                            testDrive.getId(),
                            userDTO,
                            testDrive.getDrivingLicenseNumber(),
                            testDrive.getCreateTime()
                    );
                })
                .collect(Collectors.toList());
    }
}
