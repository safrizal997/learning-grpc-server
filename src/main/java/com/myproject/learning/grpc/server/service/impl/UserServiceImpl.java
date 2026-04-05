package com.myproject.learning.grpc.server.service.impl;

import com.myproject.learning.grpc.server.model.UserDetail;
import com.myproject.learning.grpc.server.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final Map<String, UserDetail> userStore = new HashMap<>();

    public UserServiceImpl() {
        userStore.put("U001", new UserDetail("U001", "Budi Santoso", "budi@example.com", 28, "081234567890"));
        userStore.put("U002", new UserDetail("U002", "Siti Rahayu", "siti@example.com", 32, "089876543210"));
    }

    @Override
    public Optional<UserDetail> getUserDetail(String userId) {
        return Optional.ofNullable(userStore.get(userId));
    }
}
