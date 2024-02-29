package com.hukahuka.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hukahuka.order.entity.OrderProductEntity;
import com.hukahuka.order.entity.OrdersEntity;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Integer>{

	// input: orderId	output: OrdersEntity
	public OrderProductEntity findByorderId(int ourderId);
	
	// input: orderId	output: List<OrderProductEntity>
	public List<OrderProductEntity> findAllByorderId(int ourderId);
	
}
