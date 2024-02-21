package com.hukahuka.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hukahuka.order.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

}
