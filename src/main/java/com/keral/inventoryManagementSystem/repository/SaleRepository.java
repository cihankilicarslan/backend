package com.keral.inventoryManagementSystem.repository;

import com.keral.inventoryManagementSystem.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
