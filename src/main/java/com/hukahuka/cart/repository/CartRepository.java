package com.hukahuka.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hukahuka.cart.entity.CartEntity;

public interface CartRepository extends JpaRepository<CartEntity, Integer>{

}
