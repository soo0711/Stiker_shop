package com.hukahuka.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hukahuka.product.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {

}
