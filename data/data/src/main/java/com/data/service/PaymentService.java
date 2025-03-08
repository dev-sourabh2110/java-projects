package com.data.service;

import com.data.dto.PaymentDTO;
import com.data.entity.PaymentEntity;
import com.data.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<PaymentDTO> getVendorPayments(Long vendorId) {
        return paymentRepository.findByVendorId(vendorId)
                .stream()
                .map(payment -> new PaymentDTO(
                        payment.getId(), payment.getVendor().getId(),
                        payment.getAmount(), payment.getPaymentDate(),
                        payment.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
