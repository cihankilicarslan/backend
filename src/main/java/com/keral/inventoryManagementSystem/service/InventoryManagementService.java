package com.keral.inventoryManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.keral.inventoryManagementSystem.model.Product; // Doğru paket adını kullanın
import com.keral.inventoryManagementSystem.repository.InventoryRepo;

@Service
public class InventoryManagementService {

	private final InventoryRepo repo;

	@Autowired
	public InventoryManagementService(InventoryRepo repo) {
		this.repo = repo;
	}

	public Product save(Product p) {
		return repo.save(p);
	}

	public List<Product> getAllProducts() {
		return repo.findAll();
	}

	public Product getProductById(Long id) {
		Optional<Product> productOptional = repo.findById(id);
		return productOptional.orElse(null);
	}

	public void deleteProduct(Long id) {
		repo.deleteById(id);
	}
}
