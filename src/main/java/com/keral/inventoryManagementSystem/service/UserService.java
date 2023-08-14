package com.keral.inventoryManagementSystem.service;

import com.keral.inventoryManagementSystem.dto.UserDto;
import com.keral.inventoryManagementSystem.model.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();


    User save(User user);
}
