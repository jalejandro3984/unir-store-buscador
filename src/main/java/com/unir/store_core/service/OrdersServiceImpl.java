package com.unir.store_core.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unir.store_core.facade.ProductsFacade;
import com.unir.store_core.model.Product;
import com.unir.store_core.model.db.Order;
import com.unir.store_core.model.request.OrderRequest;
import com.unir.store_core.repository.OrderJpaRepository;

@Service
public class OrdersServiceImpl implements OrdersService {

  @Autowired //Inyeccion por campo (field injection). Es la menos recomendada.
  private ProductsFacade productsFacade;

  @Autowired //Inyeccion por campo (field injection). Es la menos recomendada.
  private OrderJpaRepository repository;

  @Override
  public Order createOrder(OrderRequest request) {

    List<Product> products = request.getProducts().stream().map(productsFacade::getProduct).filter(Objects::nonNull).toList();

    if(products.size() != request.getProducts().size() || products.stream().anyMatch(product -> !product.getVisible())) {
      return null;
    } else {
      Order order = Order.builder().products(products.stream().map(Product::getId).collect(Collectors.toList())).build();
      repository.save(order);
      return order;
    }
  }

  @Override
  public Order getOrder(String id) {
    return repository.findById(Long.valueOf(id)).orElse(null);
  }

  @Override
  public List<Order> getOrders() {
    List<Order> orders = repository.findAll();
    return orders.isEmpty() ? null : orders;
  }
}
