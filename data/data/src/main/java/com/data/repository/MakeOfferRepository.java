package com.data.repository;

import com.data.entity.MakeOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MakeOfferRepository extends JpaRepository<MakeOfferEntity, Long> {
   // Optional<MakeOfferEntity> findByEmail(String email);
    List<MakeOfferEntity> findByStatus(String status);
    @Query("SELECT COUNT(o.id) FROM MakeOfferEntity o")
    long getTotalOffers();
}
