package org.example;
import org.example.entity.Inventory;
import org.example.entity.Product;
import org.example.entity.Supplier;


public class Main {
    public static void main(String[] args) {
        Product p1 = new Product(1, "Laptop", "Electronics", 8, 350000, "2027-12-31");
        Product p2 = new Product(2, "Mouse", "Accessories", 60, 5000, "2028-01-01");
        Product p3 = new Product(3, "Keyboard", "Accessories", 30, 15000, "2028-05-01");

        Supplier s1 = new Supplier(101, "TechSupply", "+7 777 123 45 67", "tech@supply.kz", "Almaty", 5);
        Supplier s2 = new Supplier(102, "OfficeGoods", "+7 701 987 65 43", "office@goods.kz", "Nur-Sultan", 4);

        Inventory i1 = new Inventory(p1, s1, "Warehouse A", 10);
        Inventory i2 = new Inventory(p2, s1, "Warehouse B", 20);
        Inventory i3 = new Inventory(p3, s2, "Warehouse A", 15);

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(i1);
        System.out.println(i2);
        System.out.println(i3);

        if (p1.getTotalValue() > p2.getTotalValue()) {
            System.out.println(p1.getProductName() + " has higher total value than " + p2.getProductName());
        } else {
            System.out.println(p2.getProductName() + " has higher total value than " + p1.getProductName());
        }

        if (p1.getTotalValue() > p3.getTotalValue()) {
            System.out.println(p1.getProductName() + " has higher total value than " + p3.getProductName());
        } else {
            System.out.println(p3.getProductName() + " has higher total value than " + p1.getProductName());
        }

        if (p2.getTotalValue() > p3.getTotalValue()) {
            System.out.println(p2.getProductName() + " has higher total value than " + p3.getProductName());
        } else {
            System.out.println(p3.getProductName() + " has higher total value than " + p2.getProductName());
        }

        if (i1.needsReorder()) {
            System.out.println(i1.getProduct().getProductName() + " needs reorder!");
        }
        if (i2.needsReorder()) {
            System.out.println(i2.getProduct().getProductName() + " needs reorder!");
        }
        if (i3.needsReorder()) {
            System.out.println(i3.getProduct().getProductName() + " needs reorder!");
        }
    }
}


