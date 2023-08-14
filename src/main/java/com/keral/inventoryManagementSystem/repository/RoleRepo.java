package com.keral.inventoryManagementSystem.repository;

import com.keral.inventoryManagementSystem.model.Role;
import com.keral.inventoryManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
    Role findByName(String role_admin);
}
