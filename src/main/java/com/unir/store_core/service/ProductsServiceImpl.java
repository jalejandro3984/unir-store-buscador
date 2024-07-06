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
  public List<Product> search(String keyword) {
    return repository.search(keyword);
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

    if (request != null && (request.getName() != null ? StringUtils.hasLength(request.getName().trim()) : null)
            && (request.getDescription() != null ?  StringUtils.hasLength(request.getDescription().trim()): null)
            && (request.getCategoryName() != null ? StringUtils.hasLength(request.getCategoryName().trim()): null)
            && request.getVisible() != null) {

      Product product = Product.builder().id(request.getId()).name(request.getName()).description(request.getDescription())
              .categoryName(request.getCategoryName()).price(request.getPrice()).quantity(request.getQuantity())
              .category_id(request.getCategory_id()).image(request.getImage()).visible(request.getVisible()).build();

      return repository.save(product);
    } else {
      return null;
    }
  }
  @Override
  public Boolean updateProduct(CreateProductRequest request) {
    Product product = repository.findById(request.getId()).orElse(null);
    if (product != null){
      product.setQuantity(request.getQuantity()!=null?request.getQuantity():product.getQuantity());
      product.setVisible(request.getVisible()!=null?request.getVisible():product.getVisible());
      repository.save(product);
      return Boolean.TRUE;
    } else {
      return Boolean.FALSE;
    }
  }

}