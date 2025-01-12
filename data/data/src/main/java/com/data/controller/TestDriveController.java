package com.data.controller;

import com.data.entity.Appointment;
import com.data.entity.UserEntity;
import com.data.pojo.TestDriveRequest;
import com.data.pojo.response.AppointmentDTO;
import com.data.repository.AppointmentRepository;
import com.data.repository.UserRepository;
import com.data.service.TestDriveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users/test-drive")
public class TestDriveController {

    @Autowired
    private TestDriveService testDriveService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    // POST endpoint to submit test drive request
//    @Secured("ROLE_USER")
//    @PreAuthorize("hasRole('USER')")
//    @PostMapping("/request")
//    public ResponseEntity<String> requestTestDrive(@RequestBody @Valid TestDriveRequest testDriveRequest, BindingResult result) {
//        String responseMessage = testDriveService.saveTestDriveRequest(testDriveRequest, result);
//
//        if (result.hasErrors()) {
//            return ResponseEntity.badRequest().body(responseMessage);
//        }
//
//        return ResponseEntity.ok(responseMessage);
//    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/request")
    public ResponseEntity<String> addTestDrive(@RequestBody TestDriveRequest testDriveRequest) {
        String result = testDriveService.saveTestDriveRequest(testDriveRequest);
        return ResponseEntity.ok(result);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/appointments")
    public ResponseEntity<?> getAppointments(@AuthenticationPrincipal UserDetails userDetails) {
        // Step 1: Fetch the user based on the email
        Optional<UserEntity> userOptional = userRepository.findByEmail(userDetails.getUsername());

        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        UserEntity user = userOptional.get();

        // Step 2: Fetch the test drives linked to this user
        List<Appointment> appointments = appointmentRepository.findByTestDriveEmail(userDetails.getUsername());

        // Step 3: Convert to DTOs
        List<AppointmentDTO> appointmentDTOs = appointments.stream()
                .map(a -> new AppointmentDTO(a.getCarModel(), a.getAppointmentDate()))
                .collect(Collectors.toList());

        // Step 4: Return the appointments as a response
        if (appointmentDTOs.isEmpty()) {
            return ResponseEntity.ok("No appointments found for this user.");
        } else {
            return ResponseEntity.ok(appointmentDTOs);
        }
    }

}
