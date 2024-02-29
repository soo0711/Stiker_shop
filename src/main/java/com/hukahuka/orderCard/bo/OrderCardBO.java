package com.hukahuka.orderCard.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.order.bo.OrderProductBO;
import com.hukahuka.order.bo.OrdersBO;
import com.hukahuka.order.entity.OrderProductEntity;
import com.hukahuka.order.entity.OrdersEntity;
import com.hukahuka.orderCard.domain.OrderCard;
import com.hukahuka.product.bo.ProductBO;
import com.hukahuka.product.domain.Product;
import com.hukahuka.product.domain.ProductImage;

@Service
public class OrderCardBO {
	@Autowired
	private OrdersBO ordersBO;
	
	@Autowired
	private OrderProductBO orderProductBO;
	
	@Autowired
	private ProductBO productBO;

	public List<OrderCard> generateOrderCardList(int userId){
		
		List<OrderCard> orderCardList = new ArrayList<>();
		
		// 주문 목록 가져오기
		List<OrdersEntity> orderList = ordersBO.getOrdersListByUserId(userId);
		
		// 상품 + 이미지 넣기
		for (OrdersEntity order : orderList) {
			OrderCard orderCard = new OrderCard();
			
			// 주문서 1개
			orderCard.setOrders(order);
			
			// 주문된 상품 목록
			List<OrderProductEntity> orderProduct = orderProductBO.getOrderProduct(order.getId());
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
			
			// 리스트에 추가
			orderCardList.add(orderCard);
		}
		
		return orderCardList;
	}
}
