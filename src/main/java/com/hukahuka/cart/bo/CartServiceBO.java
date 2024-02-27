package com.hukahuka.cart.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.cart.entity.CartEntity;
import com.hukahuka.menuCard.bo.MenuCardBO;
import com.hukahuka.menuCard.domain.MenuCard;

@Service
public class CartServiceBO {
	
	@Autowired
	private CartBO cartBO;
	
	@Autowired
	private MenuCardBO menuCardBO;
	
	// input: userId	output: List<MenuCard>
	public List<Map<String, Object>> getMenuCardListByCartAndProduct(int userId) {
		List<CartEntity> cart = cartBO.getCartEntityByUserId(userId);
		
		List<Map<String, Object>> menuList = new ArrayList<>();
		
		for (int i = 0;  i < cart.size(); i++) {
			
			MenuCard menuCard = menuCardBO.generateMenuCardByProductId(cart.get(i).getProductId());
	
			Map<String, Object> map = new HashMap<>();
			map.put("productId", cart.get(i).getProductId());
			map.put("count", cart.get(i).getCount());
			map.put("menuCard", menuCard);
			
			menuList.add(map);
		}
		
		
		return menuList;
	}
}
