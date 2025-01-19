package com.data.repository;

import com.data.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<VendorEntity, Long> {
    Optional<VendorEntity> findByUserEmail(String email);
}

