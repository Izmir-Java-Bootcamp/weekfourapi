package com.kodluyoruz.weekfourapi.service;

import com.kodluyoruz.weekfourapi.exception.NotFoundException;
import com.kodluyoruz.weekfourapi.model.Product;
import com.kodluyoruz.weekfourapi.model.request.CreateUpdateProductRequest;
import com.kodluyoruz.weekfourapi.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductService {
    private final List<Product> productList;
    private final ProductRepository repository;

    private static int idCount = 0;

    public ProductService(ProductRepository repository) {
        productList = new ArrayList<>();
        this.repository = repository;
    }

    public Product getProduct(int id) {
        try {
            return repository
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException("not found"));
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException(e.getMessage());
        }
    }

    public Product getProductFromMemory(int id) {
        return productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("not found"));
    }

    public void createProduct(CreateUpdateProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();

        repository.save(product);
    }

    public void updateProduct(int id, CreateUpdateProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .build();

        repository.update(id, product);
    }

    public void deleteProduct(int id) {
        repository.delete(id);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }
}
