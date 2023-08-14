package com.keral.inventoryManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.keral.inventoryManagementSystem.model.User;

public interface UserRepo extends JpaRepository<User, Long>{

    User findByEmail(String email);
}