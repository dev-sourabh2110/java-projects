package com.data.repository;

import com.data.entity.UserHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistoryEntity, Long> {
    List<UserHistoryEntity> findByUserEmailAndHistoryType(String userEmail, String historyType);
}

