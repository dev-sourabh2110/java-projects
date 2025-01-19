package com.data.service;

import com.data.entity.UserHistoryEntity;
import com.data.repository.UserHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHistoryService {
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    public List<UserHistoryEntity> getUserHistoryByType(String userEmail, String type) {
        return userHistoryRepository.findByUserEmailAndHistoryType(userEmail, type);
    }
}

