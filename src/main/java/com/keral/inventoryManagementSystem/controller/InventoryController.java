package com.keral.inventoryManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.keral.inventoryManagementSystem.model.Product;
import com.keral.inventoryManagementSystem.service.InventoryManagementService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryManagementService inService;

	/*@PostMapping(value = "/save")
	public ResponseEntity<Product> addProduct(@ModelAttribute Product p) {
		Product savedProduct = inService.save(p);
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}*/
	@PostMapping("/save")
	public String addProduct(@ModelAttribute Product p, Model model, RedirectAttributes redirectAttributes) {
		Product savedProduct = inService.save(p);
		redirectAttributes.addFlashAttribute("message", "Product added successfully!");

		return "redirect:/inventory/products";
	}


	@GetMapping("/products")
	public String listProducts(Model model) {
		List<Product> products = inService.getAllProducts();
		model.addAttribute("products", products);
		return "products";
	}
	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable Long id, Model model) {
		Product product = inService.getProductById(id);
		model.addAttribute("product", product);
		return  "update";
	}

	@PostMapping("/update/{id}")
	public String updateProduct(@PathVariable Long id, @ModelAttribute Product updatedProduct) {
		Product existingProduct = inService.getProductById(id);
		if (existingProduct != null) {
			existingProduct.setProductName(updatedProduct.getProductName());
			existingProduct.setCategory(updatedProduct.getCategory());
			existingProduct.setQuantity(updatedProduct.getQuantity());
			existingProduct.setPrice(updatedProduct.getPrice());
			inService.save(existingProduct);
		}
		return "redirect:/inventory/products";
	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable Long id) {
		inService.deleteProduct(id);
		return "redirect:/inventory/products";
	}


	@GetMapping("/")
	public ResponseEntity<String> getInventoryHome() {
		return new ResponseEntity<>("Welcome to Inventory!", HttpStatus.OK);
	}

	// other


}
