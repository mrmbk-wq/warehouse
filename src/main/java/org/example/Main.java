package org.example;

import org.example.entity.Product;

import java.math.BigDecimal;
import java.sql.*;

public class Main {
    private static final String URL  = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "Abai12??";

    public static void main(String[] args) {
        Product[] initialProducts = {
                new Product("Laptop", 450000.0, 20),
                new Product("Mouse", 9000.0, 100)
        };

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(URL, USER, PASS);
            conn.setAutoCommit(false);
            System.out.println("Connected to PostgreSQL!");
            try (Statement st = conn.createStatement()) {
                st.execute("""
                    CREATE TABLE IF NOT EXISTS products (
                        id SERIAL PRIMARY KEY,
                        name TEXT,
                        price NUMERIC,
                        quantity INT
                    )
                """);
                st.execute("TRUNCATE TABLE products");
            } catch (SQLException e) {
                throw new SQLException("Ошибка на CREATE/TRUNCATE таблицы products", e);
            }
            String insertSQL = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(insertSQL)) {
                for (Product p : initialProducts) {
                    ps.setString(1, p.getName());
                    ps.setBigDecimal(2, BigDecimal.valueOf(p.getPrice())); // NUMERIC үшін дұрысы
                    ps.setInt(3, p.getQuantity());
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                throw new SQLException("Ошибка при INSERT в products", e);
            }

            System.out.println("--- Тізім БД-дан ---");
            try (Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery("SELECT name, price, quantity FROM products")) {

                while (rs.next()) {
                    String name = rs.getString("name");
                    BigDecimal price = rs.getBigDecimal("price");
                    int qty = rs.getInt("quantity");
                    System.out.println(name + " | " + price + " | " + qty);
                }
            } catch (SQLException e) {
                throw new SQLException("Ошибка при SELECT из products", e);
            }

            try (PreparedStatement up = conn.prepareStatement(
                    "UPDATE products SET quantity = ? WHERE name = ?")) {
                up.setInt(1, 15);
                up.setString(2, "Laptop");
                int updated = up.executeUpdate();
                System.out.println("Updated rows: " + updated);
            } catch (SQLException e) {
                throw new SQLException("Ошибка при UPDATE products", e);
            }
            try (PreparedStatement del = conn.prepareStatement(
                    "DELETE FROM products WHERE price < ?")) {
                del.setBigDecimal(1, BigDecimal.valueOf(1000.0));
                int deleted = del.executeUpdate();
                System.out.println("Deleted rows: " + deleted);
            } catch (SQLException e) {
                throw new SQLException("Ошибка при DELETE из products", e);
            }

            conn.commit();
            System.out.println("Transaction COMMIT ✅");

        } catch (SQLException e) {
            System.err.println("❌ SQL ERROR: " + e.getMessage());
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("ErrorCode: " + e.getErrorCode());
            e.printStackTrace();

            if (conn != null) {
                try {
                    conn.rollback();
                    System.err.println("Transaction ROLLBACK ✅");
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.err.println("❌ GENERAL ERROR: " + e.getMessage());
            e.printStackTrace();

            if (conn != null) {
                try {
                    conn.rollback();
                    System.err.println("Transaction ROLLBACK ✅");
                } catch (SQLException ex) {
                    System.err.println("Rollback failed: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                    System.out.println("Connection closed.");
                } catch (SQLException e) {
                    System.err.println("Close connection failed: " + e.getMessage());
                }
            }
        }
    }
}
