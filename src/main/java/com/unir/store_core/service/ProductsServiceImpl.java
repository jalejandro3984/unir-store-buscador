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

  @Override
  public Product getProduct(String productId) {
    return repository.findById(productId).orElse(null);
  }

  @Override
  public Boolean removeProduct(String productId) {

    Product product = repository.findById(productId).orElse(null);
    if (product != null) {
      repository.delete(product);
      return Boolean.TRUE;
    } else {
      return Boolean.FALSE;
    }
  }
  @Override
  public Product createProduct(CreateProductRequest request) {

    if (request != null && StringUtils.hasLength(request.getName().trim())
            && StringUtils.hasLength(request.getDescription().trim())
            && StringUtils.hasLength(request.getCategoryId().trim()) && request.getVisible() != null) {

      Product product = Product.builder().name(request.getName()).description(request.getDescription())
              .categoryId(request.getCategoryId()).price(request.getPrice()).build();

      return repository.save(product);
    } else {
      return null;
    }
  }


}