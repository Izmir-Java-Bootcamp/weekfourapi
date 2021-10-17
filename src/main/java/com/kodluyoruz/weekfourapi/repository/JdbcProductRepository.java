package com.kodluyoruz.weekfourapi.repository;

import com.kodluyoruz.weekfourapi.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class JdbcProductRepository implements ProductRepository {
    private final JdbcTemplate jdbcTemplate;


    @Override
    public Optional<Product> findById(int id) {
        String query = "Select * from products where deleted=false and id=" + id;
        return jdbcTemplate.queryForObject(query, (rs, rowNum) ->
                Optional.of(Product.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .description(rs.getString("description"))
                        .price(rs.getDouble("price"))
                        .build())
        );
    }

    @Override
    public List<Product> findAll() {
        String query = "select * from products where deleted=false";
        return jdbcTemplate.query(query, (rs, rowNum) ->
                Product.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .description(rs.getString("description"))
                        .price(rs.getDouble("price"))
                        .build());
    }

    @Override
    public int delete(int id) {
        String query = "update products set deleted=true where id=?";
//        String query = "delete from products where id=?";
        return jdbcTemplate.update(query, id);
    }

    @Override
    public int save(Product product) {
        String query = "insert into products (creationDate,deleted,name,description,price) values(?,?,?,?,?)";
        return jdbcTemplate.update(query,
                new Date(), false, product.getName(), product.getDescription(), product.getPrice());
    }

    @Override
    public int update(int id, Product product) {
        String query = "update products set name=?,description=?,price=?,lastModificationDate=? where id=?";
        return jdbcTemplate.update(query,
                product.getName(), product.getDescription(), product.getPrice(), new Date(), id);
    }
}
