package com.keral.inventoryManagementSystem.controller;


import com.keral.inventoryManagementSystem.model.Product;
import com.keral.inventoryManagementSystem.model.Sale;
import com.keral.inventoryManagementSystem.service.InventoryManagementService;
import com.keral.inventoryManagementSystem.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sales")
public class SaleController {

    private final SaleService saleService;


    @Autowired
    private InventoryManagementService inventoryService;


    @Autowired
    public SaleController(SaleService saleService, InventoryManagementService inventoryService) {
        this.saleService = saleService;
        this.inventoryService = inventoryService;
    }

    @GetMapping("/create")
    public String createSaleForm(Model model) {
        List<Product> products = inventoryService.getAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("sale", new Sale());
        return "sales/create";
    }
    @PostMapping("/create")
    public String createSale(@ModelAttribute Sale sale) {
        saleService.createSale(sale);
        return "redirect:/sales";
    }

    @GetMapping
    public String getAllSales(Model model) {
        List<Sale> sales = saleService.getAllSales();
        model.addAttribute("sales", sales);
        return "sales/list";
    }

    @GetMapping("/{id}")
    public String getSaleById(@PathVariable Long id, Model model) {
        Sale sale = saleService.getSaleById(id);
        model.addAttribute("sale", sale);
        return "sales/details"; // Assuming you have a Thymeleaf template named "details.html" to display the details
    }
    @GetMapping("/details/{id}")
    public String getSaleDetails(@PathVariable Long id, Model model) {
        Sale sale = saleService.getSaleById(id);
        model.addAttribute("sale", sale);
        return "sales/details"; // Assuming you have a Thymeleaf template named "details.html" for displaying sale details
    }
    @GetMapping("/update/{id}")
    public String updateSaleForm(@PathVariable Long id, Model model) {
        Sale sale = saleService.getSaleById(id);
        List<Product> products = inventoryService.getAllProducts();
        model.addAttribute("sale", sale);
        model.addAttribute("products", products);
        return "sales/update";
    }

    @PostMapping("/update/{id}")
    public String updateSale(@PathVariable Long id, @ModelAttribute Sale sale) {
        Sale existingSale = saleService.getSaleById(id);
        existingSale.setQuantitySold(sale.getQuantitySold());
        Product selectedProduct = inventoryService.getProductById(sale.getProduct().getProductId()); // Seçilen ürünü alın
        existingSale.setProduct(selectedProduct);
        saleService.updateSale(id, existingSale);
        return "redirect:/sales";
    }


    @GetMapping("/delete/{id}")
    public String deleteSale(@PathVariable String id) {
        saleService.deleteSale(Long.valueOf(id));
        return "redirect:/sales";
    }

}
