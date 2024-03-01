package com.hukahuka.orderCard.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.order.bo.OrderProductBO;
import com.hukahuka.order.entity.OrderProductEntity;
import com.hukahuka.orderCard.domain.OrderCard;
import com.hukahuka.product.bo.ProductBO;
import com.hukahuka.product.domain.Product;
import com.hukahuka.product.domain.ProductImage;

@Service
public class OrderCardBO {
	
	@Autowired
	private OrderProductBO orderProductBO;
	
	@Autowired
	private ProductBO productBO;

	public OrderCard generateOrderCardList(int orderId){
		
		OrderCard orderCard = new OrderCard();
		
		// 주문된 상품 목록
		List<OrderProductEntity> orderProduct = orderProductBO.getOrderProduct(orderId);
		orderCard.setOrderProduct(orderProduct);
		
		List<Product> productList = new ArrayList<>();
		List<List<ProductImage>> productImageList = new ArrayList<>();
		
		// 상품 목록 가져오기
		for (OrderProductEntity orderP : orderProduct) {
			Product product = productBO.getProductByProductId(orderP.getProductId());
			
			int productId = product.getId();
			List<ProductImage> productImage = productBO.getProductImageByProductId(productId);
			
			productList.add(product);
			productImageList.add(productImage);
		}
		
		// 주문된 상품목록
		orderCard.setProduct(productList);
		
		// 상품들의 이미지
		orderCard.setProductImage(productImageList);
		
		return orderCard;
	}
}
