package org.example.entity;

import java.util.ArrayList;

public class Inventory {
    private ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        products.add(p);
    }

    public void findProduct(String name) {
        for (Product p : products) {
            if (p.getName().equalsIgnoreCase(name)) {
                System.out.println("Табылды: " + p);
            }
        }
    }

    public void showInventory() {
        for (Product p : products) {
            System.out.println(p);
        }
    }
}