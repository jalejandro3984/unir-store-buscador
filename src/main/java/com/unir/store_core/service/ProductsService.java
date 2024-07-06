package com.unir.store_core.service;

import com.unir.store_core.model.db.Product;
import com.unir.store_core.model.request.CreateProductRequest;
import com.unir.store_core.model.response.ProductsQueryResponse;

import java.util.List;

public interface ProductsService {

	List<Product> search(String keyword);

	Product getProduct(String productId);

	Boolean removeProduct(String productId);

	Product createProduct(CreateProductRequest request);

	Boolean updateProduct(CreateProductRequest request);

}
