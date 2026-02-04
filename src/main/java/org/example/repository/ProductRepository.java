package org.example.repository;

import org.example.entity.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbc;
    public ProductRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public List<Product> findAll() {
        return jdbc.query("SELECT * FROM products", (rs, rowNum) ->
                new Product(rs.getLong("id"), rs.getString("name"), rs.getDouble("price"), rs.getInt("quantity")));
    }

    public void save(Product p) {
        jdbc.update("INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)",
                p.getName(), p.getPrice(), p.getQuantity());
    }
}