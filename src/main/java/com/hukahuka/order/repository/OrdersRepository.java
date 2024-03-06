package com.hukahuka.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hukahuka.order.entity.OrdersEntity;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {
	
	// input: userId		output: List<OrdersEntity>
	public List<OrdersEntity> findByUserId(int userId);
	
	// input: userId, status		output: List<OrdersEntity>
	public List<OrdersEntity> findByUserIdAndStatus(int userId, String status);
	
	@Query(value="select * from orders where createdAt < DATE_ADD(NOW(), INTERVAL -5 YEAR)", nativeQuery=true)
	public List<OrdersEntity> findList();
}
