package com.keral.inventoryManagementSystem.controller;

import com.keral.inventoryManagementSystem.dto.UserDto;
import com.keral.inventoryManagementSystem.model.User;
import com.keral.inventoryManagementSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/UserApi")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/login")
	public String loginForm() {
		return "login";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		UserDto user = new UserDto();
		model.addAttribute("user", user);
		return "register"; // "register.html" olarak düzeltilmiş
	}

	@PostMapping("/register/save")
	public String registration(@Valid @ModelAttribute("user") UserDto userDto,
							   BindingResult result,
							   Model model) {
		User existing = userService.findByEmail(userDto.getEmail());
		if (existing != null) {
			result.rejectValue("email", null, "There is already an account registered with that email");
		}
		if (result.hasErrors()) {
			model.addAttribute("user", userDto);
			return "register";
		}

		userService.saveUser(userDto);
		return "redirect:/UserApi/register?success";
	}

	@GetMapping("/users")
	public String listRegisteredUsers(Model model) {
		List<UserDto> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "users";
	}

	@PostMapping("/user")
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User savedUser = userService.save(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@GetMapping("/index")
	public String redirectToIndex() {
		return "index";
	}

	@GetMapping("/logout-success")
	public String logoutSuccess() {
		return "redirect:/UserApi/index";
	}
}
