package com.hukahuka.orderCard.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.orderCard.domain.OrderCard;

@Service
public class OrderCardServiceBO {
	
	@Autowired
	private OrderCardBO orderCardBO;
	
	// input: userId		output: OrderCard
	public List<OrderCard> getOrderList(int userId) {
		return orderCardBO.generateOrderCardList(userId);
	}

}
