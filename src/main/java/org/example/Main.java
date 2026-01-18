package org.example;

import org.example.entity.Product;
import java.sql.*;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "Abai12??";

    public static void main(String[] args) {
        Product[] initialProducts = {
                new Product("Laptop", 450000.0, 20),
                new Product("Mouse", 9000.0, 100)
        };

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Connected to PostgreSQL!");

            try (Statement st = conn.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS products (id SERIAL PRIMARY KEY, name TEXT, price NUMERIC, quantity INT)");
                st.execute("TRUNCATE TABLE products");
            }

            String insertSQL = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
                for (Product p : initialProducts) {
                    ps.setString(1, p.getName());
                    ps.setDouble(2, p.getPrice());
                    ps.setInt(3, p.getQuantity());
                    ps.executeUpdate();
                }
            }

            System.out.println("--- Тізім БД-дан ---");
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery("SELECT name, price, quantity FROM products")) {
                while (rs.next()) {
                    System.out.println(
                            rs.getString("name") + " | " +
                                    rs.getDouble("price") + " | " +
                                    rs.getInt("quantity")
                    );
                }
            }

            try (PreparedStatement up = conn.prepareStatement("UPDATE products SET quantity = ? WHERE name = ?")) {
                up.setInt(1, 15);
                up.setString(2, "Laptop");
                up.executeUpdate();
            }
            try (PreparedStatement del = conn.prepareStatement("DELETE FROM products WHERE price < ?")) {
                del.setDouble(1, 1000.0);
                del.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}