package org.example.entity;

public class Supplier {
    private Long id;
    private String name;

    public Supplier(Long id,String name) {
        this.id = id;
        this.name = name;
    }

    public Supplier(){}

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Жеткізуші: " + name;
    }
}