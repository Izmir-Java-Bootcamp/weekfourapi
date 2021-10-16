package com.kodluyoruz.weekfourapi.controller;


import com.kodluyoruz.weekfourapi.model.Product;
import com.kodluyoruz.weekfourapi.model.request.CreateProductRequest;
import com.kodluyoruz.weekfourapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService service;

    @GetMapping("{id}")
    public Product getProduct(@PathVariable int id) {
        return service.getProduct(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody CreateProductRequest request) {
        return service.createProduct(request);
    }
}
