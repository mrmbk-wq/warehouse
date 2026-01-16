package org.example;

import java.sql.*;

public class Main {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Abai12??";

    public static void main(String[] args) {

        String[] productNames = {
                "Laptop", "Mouse", "Keyboard", "Monitor", "Printer",
                "Scanner", "Webcam", "Headphones", "USB Flash Drive", "External Hard Drive"
        };

        double[] prices = {
                450000, 8000, 15000, 120000, 95000,
                87000, 22000, 18000, 6000, 65000
        };

        int[] quantities = {
                20, 100, 60, 25, 15,
                10, 40, 55, 200, 18
        };

        String insertProductSQL =
                "INSERT INTO product (name, price) VALUES (?, ?) RETURNING product_id";
        String insertInventorySQL =
                "INSERT INTO inventory (product_id, quantity) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.println("Connected to PostgreSQL successfully!");

            for (int i = 0; i < productNames.length; i++) {

                PreparedStatement productStmt =
                        connection.prepareStatement(insertProductSQL);
                productStmt.setString(1, productNames[i]);
                productStmt.setDouble(2, prices[i]);

                var resultSet = productStmt.executeQuery();
                resultSet.next();
                int productId = resultSet.getInt("product_id");

                PreparedStatement inventoryStmt =
                        connection.prepareStatement(insertInventorySQL);
                inventoryStmt.setInt(1, productId);
                inventoryStmt.setInt(2, quantities[i]);
                inventoryStmt.executeUpdate();
            }

            String selectQuery = """
                        SELECT p.name, p.price, i.quantity
                        FROM product p
                        JOIN inventory i ON p.product_id = i.product_id
                    """;

            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(selectQuery);

            while (rs.next()) {
                System.out.println(
                        rs.getString("name") + " | " +
                                rs.getDouble("price") + " | " +
                                rs.getInt("quantity")
                );
            }

            String updateQuery = "UPDATE inventory SET quantity = ? WHERE product_id = ?";
            PreparedStatement ps2 = connection.prepareStatement(updateQuery);
            ps2.setInt(1, 15);
            ps2.setInt(2, 1);
            ps2.executeUpdate();

            String deleteQuery = "DELETE FROM product WHERE product_id = ?";
            PreparedStatement ps3 = connection.prepareStatement(deleteQuery);
            ps3.setInt(1, 1);
            ps3.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
