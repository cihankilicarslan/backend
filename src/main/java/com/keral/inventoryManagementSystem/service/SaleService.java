package com.keral.inventoryManagementSystem.service;

import com.keral.inventoryManagementSystem.model.Sale;
import com.keral.inventoryManagementSystem.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;

    @Autowired
    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    public void createSale(Sale sale) {
        saleRepository.save(sale);
    }

    public void updateSale(Long id, Sale sale) {
        Sale existingSale = saleRepository.findById(id).orElse(null);
        if (existingSale != null) {
            existingSale.setQuantitySold(sale.getQuantitySold());
            existingSale.setProduct(sale.getProduct());
            saleRepository.save(existingSale);
        }
    }




    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }
}
