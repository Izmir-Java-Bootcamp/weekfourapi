package com.kodluyoruz.weekfourapi.repository;

import com.kodluyoruz.weekfourapi.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(int id);

    List<Product> findAll();

    int delete(int id);

    int save(Product product);

    int update(int id, Product product);
}
