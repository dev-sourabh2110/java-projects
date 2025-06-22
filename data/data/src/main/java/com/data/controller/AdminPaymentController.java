package com.data.controller;

import com.data.dto.PaymentDTO;
import com.data.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/payments")
public class AdminPaymentController {

    private final PaymentService paymentService;

    public AdminPaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Get all vendor payments
     */
  //  @Secured("ROLE_ADMIN")
    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments(@RequestParam Long vendorId) {
        return ResponseEntity.ok(paymentService.getVendorPayments(vendorId));
    }
}
