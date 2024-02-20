package com.hukahuka.cart.bo;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.cart.entity.CartEntity;
import com.hukahuka.cart.repository.CartRepository;
import com.hukahuka.menuCard.bo.MenuCardBO;
import com.hukahuka.menuCard.domain.MenuCard;

@Service
public class CartBO {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private MenuCardBO menuCardBO;


	// input: productId, userId, count		output: X
	public void addCartEntity(int productId, int userId, int count) {
		cartRepository.save(
				CartEntity.builder()
				.productId(productId)
				.userId(userId)
				.count(count)
				.build()
				);
	}
	
	// input: userId	output: List<MenuCard>
	public List<Map<String, Object>> getMenuCardListByCartAndProduct(int userId) {
		List<CartEntity> cart = cartRepository.findByUserId(userId);;
		
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
	
	// input:userId, productId		output:CartEntity
	public CartEntity getCartEntityByUserIdAndProductId(int userId, int productId) {
		return cartRepository.findByUserIdAndProductId(userId, productId);
	}
	
	// input: userId, productId		output: void
	public void deleteCartEntityByUserIdAndProductId(int userId, int productId) {
		CartEntity cart = cartRepository.findByUserIdAndProductId(userId, productId);
		if (cart != null) {
		    cartRepository.delete(cart);
		}
	}
	
	// input: userId, productId		output: void
	public void updateCartEntityByUserIdAndProductId(int userId, int productId, int count) {
		CartEntity cart = cartRepository.findByUserIdAndProductId(userId, productId);
        cart = cart.toBuilder()
        		.count(count)
                .build();
        cartRepository.save(cart); 
	}
}
