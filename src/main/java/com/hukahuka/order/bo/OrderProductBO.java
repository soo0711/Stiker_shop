package com.hukahuka.order.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.order.entity.OrderProductEntity;
import com.hukahuka.order.repository.OrderProductRepository;

@Service
public class OrderProductBO {

	@Autowired
	private OrderProductRepository orderProductRepository;
	
	// input: params		output: X
	public void addProductOrder(int orderId, int[] productId, int[] count) {
		for (int i = 0; i < productId.length; i++) {
			orderProductRepository.save(
					OrderProductEntity.builder()
					.orderId(orderId)
					.productId(productId[i])
					.count(count[i])
					.build());
		}
	}
	
	// input: orderId		output: X
	public void deleteOrderProductByorderId(int orderId){
		OrderProductEntity orders = orderProductRepository.findByorderId(orderId);
		if (orders != null) {
			orderProductRepository.delete(orders);
		}
	}
	
	// input: productId		output: OrderProductEntity
	public List<OrderProductEntity> getOrderProduct(int orderId) {
		return orderProductRepository.findAllByorderId(orderId);
	}
}
