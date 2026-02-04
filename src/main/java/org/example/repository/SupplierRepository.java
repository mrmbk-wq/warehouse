package org.example.repository;


import org.example.entity.Supplier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SupplierRepository {
    private final JdbcTemplate jdbc;
    public SupplierRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public List<Supplier> findAll() {
        return jdbc.query("SELECT * FROM suppliers", (rs, rowNum) ->
                new Supplier(rs.getLong("id"), rs.getString("name")));
    }

    public void save(Supplier s) {
        jdbc.update("INSERT INTO suppliers (name) VALUES (?)", s.getName());
    }
}
