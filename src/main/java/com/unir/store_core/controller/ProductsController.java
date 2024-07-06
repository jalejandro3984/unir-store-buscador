package com.unir.store_core.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.unir.store_core.model.request.CreateProductRequest;
import com.unir.store_core.service.ProductsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import com.unir.store_core.model.response.ProductsQueryResponse;
import org.springframework.http.HttpStatus;

import com.unir.store_core.model.db.Product;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProductsController {

    private final ProductsService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(String keyword) {
        List<Product> products = service.search(keyword);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable String productId) {

        log.info("Request received for product {}", productId);
        Product product = service.getProduct(productId);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String productId) {

        Boolean removed = service.removeProduct(productId);

        if (Boolean.TRUE.equals(removed)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping("/products")
    public ResponseEntity<Product> getProduct(@RequestBody CreateProductRequest request) {

        Product createdProduct = service.createProduct(request);

        if (createdProduct != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @PatchMapping("/products/update")
    public ResponseEntity<Product> updateProduct(@RequestBody CreateProductRequest request) {

        Boolean updated = service.updateProduct(request);

        if (Boolean.TRUE.equals(updated)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }

    }



}
