package com.hukahuka.wish.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.cart.entity.CartEntity;
import com.hukahuka.menuCard.bo.MenuCardBO;
import com.hukahuka.menuCard.domain.MenuCard;
import com.hukahuka.wish.entity.WishEntity;
import com.hukahuka.wish.repository.WishRepository;

@Service
public class WishBO {

	@Autowired
	private WishRepository wishRepository;
	
	@Autowired
	private MenuCardBO	menuCardBO;
	
	// input: productId, userId	output: X
	public void wishToggle(int productId, int userId) {
		// wish가 있으면
		if(getWishEntityByUserIdAndProductId(userId, productId) != null) {
			// -- 삭제
			deleteWishEntityByUserIdAndProductId(userId, productId);
		} else {
			// 없으면
			// -- 추가
			addWishEntity(productId, userId);
		}
	}
	
	
	// input: productId, userId, count		output: X
	public void addWishEntity(int productId, int userId) {
		wishRepository.save(
				WishEntity.builder()
				.productId(productId)
				.userId(userId)
				.build()
				);
	}
	
	// input: productId, userId 		output: WishEntity
	public WishEntity getWishEntityByUserIdAndProductId(int userId, int productId) {
		return wishRepository.findByUserIdAndProductId(userId, productId);
	}
	
	// input: userId	output: List<MenuCard>
	public List<Map<String, Object>> getMenuCardListByWishAndProduct(int userId) {
		List<WishEntity> wish = wishRepository.findByUserId(userId);
		
		List<Map<String, Object>> wishList = new ArrayList<>();
		
		for (int i = 0;  i < wish.size(); i++) {
			
			MenuCard menuCard = menuCardBO.generateMenuCardByProductId(wish.get(i).getProductId());
	
			Map<String, Object> map = new HashMap<>();
			map.put("productId", wish.get(i).getProductId());
			map.put("menuCard", menuCard);
			
			wishList.add(map);
		}
		
		
		return wishList;
	}
	
	// input: userId, productId		output: void
	public void deleteWishEntityByUserIdAndProductId(int userId, int productId) {
		WishEntity wish = wishRepository.findByUserIdAndProductId(userId, productId);
		if (wish != null) {
		    wishRepository.delete(wish);
		}
	}
	
	
		
}
