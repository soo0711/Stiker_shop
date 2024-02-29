package com.hukahuka.order.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.orderCard.bo.OrderCardBO;
import com.hukahuka.orderCard.domain.OrderCard;

@Service
public class OrdersServiceBO {
	
	@Autowired
	private OrderCardBO orderCardBO;
	
	public List<OrderCard> generateOrderCardList(int userId){
		return orderCardBO.generateOrderCardList(userId);
	}

}

