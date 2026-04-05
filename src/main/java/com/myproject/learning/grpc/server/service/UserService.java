package com.myproject.learning.grpc.server.service;

import com.myproject.learning.grpc.server.model.UserDetail;

import java.util.Optional;

public interface UserService {
    Optional<UserDetail> getUserDetail(String userId);
}
