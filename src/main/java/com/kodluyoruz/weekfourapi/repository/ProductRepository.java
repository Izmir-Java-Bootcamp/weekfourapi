package com.kodluyoruz.weekfourapi.repository;

import com.kodluyoruz.weekfourapi.model.Product;

import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findById(int id);
}
