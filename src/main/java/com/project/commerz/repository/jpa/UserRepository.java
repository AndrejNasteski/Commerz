package com.project.commerz.repository.jpa;

import com.project.commerz.model.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<ShopUser, Long> {
    ShopUser findUserById(Long id);
}
