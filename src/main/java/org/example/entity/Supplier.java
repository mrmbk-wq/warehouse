package org.example.entity;

public class Supplier {
    private String name;

    public Supplier(String name) {
        this.name = name;
    }

    public Supplier() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Жеткізуші: " + name;
    }
}