package org.example.entity;

import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public void showAll() {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public List<Product> getProducts() {
        return products;
    }
}