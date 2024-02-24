package com.hukahuka.order.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.menuCard.bo.MenuCardBO;
import com.hukahuka.menuCard.domain.MenuCard;

@Service
public class OrderBO {
	
	@Autowired
	private MenuCardBO menuCardBO;

	// input: productId, count		output: MenuCard
	public MenuCard getMenuCard(int productId) {
		return menuCardBO.generateMenuCardById(productId);
	}
	
	// input:[] product, [] count		output: List<MenuCard>
	public List<MenuCard> getMenuCardList(int[] product, int userId) {
		return menuCardBO.generateMenuCardListByProductId(product, userId);
	}
}
