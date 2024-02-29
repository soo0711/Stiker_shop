package com.hukahuka.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hukahuka.order.entity.OrdersEntity;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {
	
	// input: userId		output: List<OrdersEntity>
	public List<OrdersEntity> findByUserId(int userId);
}
