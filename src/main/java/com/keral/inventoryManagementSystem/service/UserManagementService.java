package com.keral.inventoryManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keral.inventoryManagementSystem.model.User;
import com.keral.inventoryManagementSystem.repository.UserRepo;

@Service
public class UserManagementService {

	
	public UserRepo userrepo;

	@Autowired
	public UserManagementService(UserRepo userrepo) {
		super();
		this.userrepo = userrepo;
	
	}
	
	public User save(User u) {
		
		System.out.println(u.getId());

		return userrepo.save(u);
	}
		
	
	
	
}
