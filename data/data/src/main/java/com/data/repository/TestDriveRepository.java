package com.data.repository;

import com.data.entity.TestDriveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDriveRepository extends JpaRepository<TestDriveEntity, Long> {
    List<TestDriveEntity> findByStatus(String status);

    @Query("SELECT COUNT(t.id) FROM TestDriveEntity t")
    long getTotalTestDrives();
}
