package com.order.service;

import java.util.List;

import com.order.entity.Order;

public interface OrderService {

	Order saveOrder(Order order);

	void deleteOrder(int orderId);

	Order updateOrder(Order order);

	Order getOrderById(int orderId);
	
	List<Order> getAllOrdersList();

	int getSequenceNumber(String sequenceName);
}
