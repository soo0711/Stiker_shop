package com.hukahuka.order.bo;

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
}
