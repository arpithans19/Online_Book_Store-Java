package com.order.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.order.entity.DBSequence;
import com.order.entity.Order;
import com.order.exception.OrderNotFoundException;
import com.order.repository.OrderRepository;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Override
	public Order saveOrder(Order order) {
		order.setOrderId(getSequenceNumber(Order.SEQUENCE_NAME));
		Order newOrder=orderRepo.save(order);
		return newOrder;
	}

	@Override
	public void deleteOrder(int orderId) {
		Optional<Order> optionalOrder=orderRepo.findById(orderId);
		if(optionalOrder.isEmpty()) {
			throw new OrderNotFoundException("Order not found with this id: "+orderId);
			
		}
		orderRepo.deleteById(orderId);
	}

	@Override
	public Order updateOrder(Order order) {
		Optional<Order> optionalOrder=orderRepo.findById(order.getOrderId());
		if(optionalOrder.isEmpty()) {
			throw new OrderNotFoundException("Order not found with this id: "+order.getOrderId());
			
		}
		return orderRepo.save(order);
	}

	@Override
	public Order getOrderById(int orderId) {
		Optional<Order> optionalOrder=orderRepo.findById(orderId);
		if(optionalOrder.isEmpty()) {
			throw new OrderNotFoundException("Order not found with this id: "+orderId);
			
		}
		return optionalOrder.get() ;
	}

	@Override
	public List<Order> getAllOrdersList() {
		List<Order> allOrders=orderRepo.findAll();
		if(allOrders.isEmpty()) {
			throw new OrderNotFoundException("No orders made ");
		}
		return allOrders;
	}

	@Override
	public int getSequenceNumber(String sequenceName) {
		//generate sequence no
				Query query=new Query(Criteria.where("id").is(sequenceName));
				//update the sequence no
				Update update=new Update().inc("seq",1);
				//modify in document
				DBSequence counter=mongoOperations.findAndModify(query,update, options().returnNew(true).upsert(true),DBSequence.class);
				return !Objects.isNull(counter)? counter.getSeq():1;
	}

}
