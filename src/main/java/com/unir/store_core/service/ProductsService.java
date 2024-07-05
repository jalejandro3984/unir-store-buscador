package com.unir.store_core.service;

import com.unir.store_core.model.db.Product;
import com.unir.store_core.model.request.CreateProductRequest;
import com.unir.store_core.model.response.ProductsQueryResponse;

import java.util.List;

public interface ProductsService {

	ProductsQueryResponse getProducts(List<String> priceValues,
									  List<String> categoryValues,
									  String name,
									  String description,
									  String page);

	Product getProduct(Integer productId);

	Boolean removeProduct(Integer productId);

	Product createProduct(CreateProductRequest request);

	Boolean updateProduct(CreateProductRequest request);

}
