package com.data.service;

import com.data.entity.Appointment;
import com.data.entity.TestDrive;
import com.data.pojo.TestDriveRequest;
import com.data.repository.AppointmentRepository;
import com.data.repository.TestDriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TestDriveService {

    @Autowired
    private TestDriveRepository testDriveRepository;

    @Autowired
    private AppointmentRepository appointmentRepository; // Inject AppointmentRepository

    @Transactional
    public String saveTestDriveRequest(TestDriveRequest testDriveRequest) {
        // Create a new TestDrive entity
        TestDrive testDrive = new TestDrive();
        testDrive.setName(testDriveRequest.getName());
        testDrive.setEmail(testDriveRequest.getEmail());
        testDrive.setPhoneNumber(testDriveRequest.getPhoneNumber());
        testDrive.setAddress(testDriveRequest.getAddress());
        testDrive.setDrivingLicenseNumber(testDriveRequest.getDrivingLicenseNumber());

        // Create a new Appointment entity
        Appointment appointment = new Appointment();
        appointment.setCarModel(testDriveRequest.getAppointmentRequest().getCarModel());
        appointment.setAppointmentDate(testDriveRequest.getAppointmentRequest().getAppointmentDate());
        appointment.setTestDrive(testDrive);

        // Link Appointment with TestDrive
        testDrive.getAppointments().add(appointment);

        // Save TestDrive and Appointment
        testDriveRepository.save(testDrive);
        appointmentRepository.save(appointment);

        return "Test drive and appointment saved successfully!";
    }
}
