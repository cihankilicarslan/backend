package com.keral.inventoryManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.keral.inventoryManagementSystem.model.Product;
import com.keral.inventoryManagementSystem.service.InventoryManagementService;

import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryManagementService inService;

	@PostMapping("/save")
	public ResponseEntity<Product> addProduct(@RequestBody Product p) {
		Product savedProduct = inService.save(p);
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}



	@GetMapping("/products")
	public String listProducts(Model model) {
		List<Product> products = inService.getAllProducts();
		model.addAttribute("products", products);
		return "products";
	}
	@GetMapping("/")
	public ResponseEntity<String> getInventoryHome() {
		return new ResponseEntity<>("Welcome to Inventory!", HttpStatus.OK);
	}

	// other


}
