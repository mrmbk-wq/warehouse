package org.example.entity;

public class Inventory {
    private Product product;
    private Supplier supplier;
    private String warehouseLocation;
    private int reorderLevel;

    public Inventory(Product product, Supplier supplier,
                     String warehouseLocation, int reorderLevel) {
        this.product = product;
        this.supplier = supplier;
        this.warehouseLocation = warehouseLocation;
        this.reorderLevel = reorderLevel;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public boolean needsReorder() {
        return product.getQuantity() < reorderLevel;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "product=" + product.getProductName() +
                ", supplier=" + supplier.getSupplierName() +
                ", warehouseLocation='" + warehouseLocation + '\'' +
                ", reorderLevel=" + reorderLevel +
                '}';
    }
}

