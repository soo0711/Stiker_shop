package com.hukahuka.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hukahuka.order.entity.OrdersEntity;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {

}
