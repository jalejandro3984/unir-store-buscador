package com.unir.store_core.service;

import com.unir.store_core.model.db.Order;
import com.unir.store_core.model.request.OrderRequest;
import java.util.List;

public interface OrdersService {
	
	Order createOrder(OrderRequest request);

	Order getOrder(String id);

	List<Order> getOrders();

}
