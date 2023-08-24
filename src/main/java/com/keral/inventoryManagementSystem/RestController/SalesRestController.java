package com.keral.inventoryManagementSystem.RestController;

import com.keral.inventoryManagementSystem.model.Product;
import com.keral.inventoryManagementSystem.model.Sale;
import com.keral.inventoryManagementSystem.service.InventoryManagementService;
import com.keral.inventoryManagementSystem.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SalesRestController {

    private final SaleService saleService;

    @Autowired
    private InventoryManagementService inventoryService;

    @Autowired
    public SalesRestController(SaleService saleService, InventoryManagementService inventoryService) {
        this.saleService = saleService;
        this.inventoryService = inventoryService;
    }

    @GetMapping
    public List<Sale> getAllSales() {
        return saleService.getAllSales();
    }

    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable Long id) {
        return saleService.getSaleById(id);
    }

    @PostMapping
    public Sale createSale(@RequestBody Sale sale) {
        saleService.createSale(sale);
        return sale;
    }

    @PutMapping("/{id}")
    public Sale updateSale(@PathVariable Long id, @RequestBody Sale sale) {
        Sale existingSale = saleService.getSaleById(id);
        existingSale.setQuantitySold(sale.getQuantitySold());
        Product selectedProduct = inventoryService.getProductById(sale.getProduct().getProductId());
        existingSale.setProduct(selectedProduct);
        saleService.updateSale(id, existingSale);
        return existingSale;
    }

    @DeleteMapping("/{id}")
    public void deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
    }
}
