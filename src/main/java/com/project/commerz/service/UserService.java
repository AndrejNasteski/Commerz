package com.project.commerz.service;

import com.project.commerz.model.ShopUser;

import java.util.Optional;

public interface UserService {
    Optional<ShopUser> findUserById(Long id);
}
