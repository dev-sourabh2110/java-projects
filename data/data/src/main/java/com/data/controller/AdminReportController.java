//package com.data.controller;
//
//import com.data.dto.AdminReportDTO;
//import com.data.service.ReportService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.annotation.Secured;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/admin/reports")
//public class AdminReportController {
//
//    private final ReportService reportService;
//
//    public AdminReportController(ReportService reportService) {
//        this.reportService = reportService;
//    }
//
//    @Secured("ROLE_ADMIN")
//    @GetMapping
//    public ResponseEntity<AdminReportDTO> getReports() {
//        return ResponseEntity.ok(reportService.getReports());
//    }
//}
