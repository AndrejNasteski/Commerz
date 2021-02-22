package com.project.commerz.service;

import com.project.commerz.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);
}
