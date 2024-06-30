package com.unir.store_core.service;

import com.unir.store_core.data.DataAccessRepository;
import com.unir.store_core.model.response.ProductsQueryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.unir.store_core.model.db.Product;
import com.unir.store_core.model.request.CreateProductRequest;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsServiceImpl implements ProductsService {

  private final DataAccessRepository repository;

  @Override
  public ProductsQueryResponse getProducts(List<String> priceValues,
                                           List<String> categoryValues,
                                           String name,
                                           String description,
                                           String page) {
    //Ahora por defecto solo devolvera productos visibles
    return repository.findProducts(priceValues, categoryValues, name, description, page);
  }
/*
  @Override
  public Product getProduct(String productId) {
    return repository.findById(productId).orElse(null);
  } */




}