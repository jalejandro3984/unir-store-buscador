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
    public ResponseEntity<ProductsQueryResponse> getProducts(
           // @RequestHeader Map<String, String> headers,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<String> categoryValues,
            @RequestParam(required = false) List<String> priceValues,
            @RequestParam(required = false, defaultValue = "0") String page) {

       // log.info("headers: {}", headers);
        ProductsQueryResponse products = service.getProducts(priceValues, categoryValues, name, description, page);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {

        log.info("Request received for product {}", productId);
        Product product = service.getProduct(productId);

        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer productId) {

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

        Product createdProduct = service.createProduct(request);

        if (createdProduct != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } else {
            return ResponseEntity.badRequest().build();
        }

    }



}
