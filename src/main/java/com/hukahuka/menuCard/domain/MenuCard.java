package com.hukahuka.menuCard.domain;

import java.util.List;

import com.hukahuka.cart.entity.CartEntity;
import com.hukahuka.product.domain.Product;
import com.hukahuka.product.domain.ProductImage;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MenuCard {
	// 상품 1개
	private Product product;

	// 상품 이미지 주소 
	private List<ProductImage> productImage;
	
}
