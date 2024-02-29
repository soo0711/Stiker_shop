package com.hukahuka.orderCard.domain;

import java.util.List;

import com.hukahuka.order.entity.OrderProductEntity;
import com.hukahuka.order.entity.OrdersEntity;
import com.hukahuka.product.domain.Product;
import com.hukahuka.product.domain.ProductImage;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderCard { // 주문서 1개당 - 이름, 주문수량, 개수, 상품이미지, 배송현황, 배송지, 상품이름
	
	// 주문된 상품 여러개
	private List<OrderProductEntity> orderProduct; // 주문수량, 개수
	
	// 상품 여러개
	private List<Product> product; // 상품이름

	// 상품 이미지 주소 
	private List<List<ProductImage>> productImage; // 상품이미지
}
