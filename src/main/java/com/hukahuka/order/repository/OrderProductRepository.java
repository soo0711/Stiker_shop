package com.hukahuka.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hukahuka.order.entity.OrderProductEntity;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity, Integer>{

}
