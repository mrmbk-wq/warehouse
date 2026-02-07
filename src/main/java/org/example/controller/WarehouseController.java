package org.example.controller;

import org.example.entity.Product;
import org.example.entity.Supplier;
import org.example.repository.ProductRepository;
import org.example.repository.SupplierRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class WarehouseController {

    private final ProductRepository productRepo;
    private final SupplierRepository supplierRepo;

    public WarehouseController(ProductRepository productRepo,
                               SupplierRepository supplierRepo) {
        this.productRepo = productRepo;
        this.supplierRepo = supplierRepo;
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return productRepo.findAll();
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody Product p) {
        productRepo.save(p);
    }

    @GetMapping("/suppliers")
    public List<Supplier> getSuppliers() {
        return supplierRepo.findAll();
    }

    @PostMapping("/suppliers")
    public void addSupplier(@RequestBody Supplier s) {
        supplierRepo.save(s);
    }
}

