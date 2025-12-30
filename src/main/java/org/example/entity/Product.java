import java.util.Objects;
public class Product {
    private String name;
    private double price;
    private Supplier supplier; // Композиция (Product-тың ішінде Supplier бар)
    public Product(String name, double price, Supplier supplier) {
        this.name = name;
        this.price = price;
        this.supplier = supplier;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    @Override
    public String toString() {
        return "Тауар: " + name + " | Бағасы: " + price + " | " + supplier;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(name, product.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}