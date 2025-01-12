//package com.data.controller;
//
//import com.data.entity.Appointment;
//import com.data.service.AppointmentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/user/appointments")
//public class AppointmentController {
//
//    @Autowired
//    private AppointmentService appointmentService;
//
//    @GetMapping
//    public ResponseEntity<List<Appointment>> getAppointments(@AuthenticationPrincipal String email) {
//        return ResponseEntity.ok(appointmentService.getAppointments(email));
//    }
//}
//
