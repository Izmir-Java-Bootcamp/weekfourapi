package com.kodluyoruz.weekfourapi.service;

import com.kodluyoruz.weekfourapi.exception.NotFoundException;
import com.kodluyoruz.weekfourapi.model.Product;
import com.kodluyoruz.weekfourapi.model.request.CreateUpdateProductRequest;
import com.kodluyoruz.weekfourapi.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
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
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("not found"));
    }

    public Product getProductFromMemory(int id) {
        return productList.stream()
                .filter(product -> product.getId() == id)
                .findFirst()
                .orElseThrow(() -> new NotFoundException("not found"));
    }

    public Product createProduct(CreateUpdateProductRequest request) {
        log.info("Create request: {}", request);

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .description(request.getDescription())
                .id(++idCount)
                .build();

        productList.add(product);
        return product;
    }

    public Product updateProduct(int id, CreateUpdateProductRequest request) {
        Product product = getProductFromMemory(id);
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());

        return product;
    }

    public Product deleteProduct(int id) {
        Product product = getProductFromMemory(id);
        productList.remove(product);
        return product;
    }

    public List<Product> getProducts() {
        return productList;
    }
}
