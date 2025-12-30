public class Main {
    public static void main(String[] args) {
        Supplier s1 = new Supplier("Apple Inc");
        Supplier s2 = new Supplier("Samsung Logistics");
        Product p1 = new Product("iPhone 15", 1200.0, s1);
        Product p2 = new Product("Galaxy S23", 1000.0, s2);
        Product p3 = new Product("MacBook", 2000.0, s1);
        Inventory myWarehouse = new Inventory();
        myWarehouse.addProduct(p1);
        myWarehouse.addProduct(p2);
        myWarehouse.addProduct(p3);
        System.out.println("--- Қоймадағы тауарлар тізімі ---");
        myWarehouse.showInventory();
        System.out.println("\n--- Іздеу функциясы ---");
        myWarehouse.findProduct("iPhone 15");
    }
}