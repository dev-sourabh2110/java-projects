package com.data.repository;

import com.data.entity.VendorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface VendorRepository extends JpaRepository<VendorEntity, Long> {
    Optional<VendorEntity> findByUserEmail(String email);
    Optional<VendorEntity> findByUserId(Long user_id);
    List<VendorEntity> findByEnabled(boolean enabled);

    @Query("SELECT v.tradeLicenseNumber FROM VendorEntity v JOIN v.cars c WHERE c.status = 'SOLD' GROUP BY v.tradeLicenseNumber ORDER BY COUNT(c.id) DESC LIMIT 1")
    String getMostActiveVendor();
}

