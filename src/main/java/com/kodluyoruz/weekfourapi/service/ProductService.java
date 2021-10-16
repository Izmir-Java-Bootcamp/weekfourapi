package com.kodluyoruz.weekfourapi.service;

import com.kodluyoruz.weekfourapi.model.Product;
import com.kodluyoruz.weekfourapi.model.request.CreateProductRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final List<Product> productList;
    private static int idCount = 0;

    public ProductService() {
        productList = new ArrayList<>();
    }

    public Product getProduct(int id) {
        return productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("not found"));
    }

    public Product createProduct(CreateProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .id(++idCount)
                .build();

        productList.add(product);
        return product;
    }
}
