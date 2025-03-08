package com.data.repository;

import com.data.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {

    // Find an admin by email
    Optional<AdminEntity> findByUserEmail(String email);
    Optional<AdminEntity> findByUserId(Long user_id);
}
