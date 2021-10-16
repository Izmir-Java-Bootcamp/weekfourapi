package com.kodluyoruz.weekfourapi.repository;

import com.kodluyoruz.weekfourapi.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcProductRepository implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;


    @Override
    public Optional<Product> findById(int id) {
        String query = "Select * from products where id=" + id;
        return jdbcTemplate.queryForObject(query,(rs, rowNum) ->
                Optional.of(Product.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .description(rs.getString("description"))
                        .price(rs.getDouble("price"))
                        .build())
                );
    }
}
