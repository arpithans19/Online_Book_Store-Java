package com.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.entity.Order;
import com.order.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/allOrders")

	public List<Order> getAllOrders() {
		List<Order> listOfOrders = orderService.getAllOrdersList();
		return listOfOrders;

	}

	@GetMapping("/get/{orderId}")
	public ResponseEntity<Object> getOrderById(@PathVariable("orderId") int orderId) {

		ResponseEntity<Object> responseEntity = null;
		Order order = orderService.getOrderById(orderId);
		responseEntity = new ResponseEntity<>(order, HttpStatus.OK);
		return responseEntity;
	}

	@PostMapping("/addOrder")
	public ResponseEntity<Order> addOrder(@Validated @RequestBody Order order) {
		Order newOrder = orderService.saveOrder(order);
		ResponseEntity<Order> responseEntity = new ResponseEntity<>(newOrder, HttpStatus.OK);
		return responseEntity;

	}

	@DeleteMapping("/delete/{orderId}")
	public ResponseEntity<String> removeOrder(@PathVariable("orderId") int orderId) {
		orderService.deleteOrder(orderId);
		ResponseEntity<String> responseEntity = new ResponseEntity<>(
				"Order deleted successfully with " + orderId + " id!!", HttpStatus.OK);
		return responseEntity;
	}
	@PutMapping("/update")
	public ResponseEntity<Order> updateOderDetails(@RequestBody Order order) {
		Order updatedOrder = orderService.updateOrder(order);
		ResponseEntity<Order> responseEntity = new ResponseEntity<>(updatedOrder, HttpStatus.OK);
		return responseEntity;
	}
}
