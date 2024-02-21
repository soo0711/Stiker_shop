package com.hukahuka.product.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.menuCard.bo.MenuCardBO;
import com.hukahuka.menuCard.domain.MenuCard;
import com.hukahuka.wish.bo.WishBO;

@Service
public class ProductServiceBO {

	@Autowired
	private MenuCardBO menuCardBO;
	
	@Autowired
	private WishBO wishBO;
	
	// input: userId, productId		output: boolean
	public boolean isWish(int userId, int productId) {
		return wishBO.getWishEntityByUserIdAndProductId(userId, productId) == null? true : false; // null 이면 true
	}
	
	// input: productId		output: MenuCard
	public MenuCard generateMenuCardById(int productId) {
		return menuCardBO.generateMenuCardById(productId);
	}
}
