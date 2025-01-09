package com.data.repository;

import com.data.entity.MakeOfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MakeOfferRepository extends JpaRepository<MakeOfferEntity, Long> {
    Optional<MakeOfferEntity> findByEmail(String email);
}
