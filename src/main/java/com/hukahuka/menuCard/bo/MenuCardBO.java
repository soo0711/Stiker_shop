package com.hukahuka.menuCard.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.cart.bo.CartBO;
import com.hukahuka.cart.entity.CartEntity;
import com.hukahuka.menuCard.domain.MenuCard;
import com.hukahuka.product.bo.ProductBO;
import com.hukahuka.product.domain.Product;
import com.hukahuka.product.domain.ProductImage;

@Service
public class MenuCardBO {
	
	@Autowired
	private CartBO cartBO;
	
	@Autowired
	private ProductBO productBO;
	
	// input: X		output:List<MenuCard>
	// 가공하는 view를 사용할 때는 generate
	public List<MenuCard> generateMenuCardList(){
		
		List<MenuCard> menuCardList = new ArrayList<>();
		
		// 상품 목록 가져오기
		List<Product> productList = productBO.getProductList();
		
		// 상품 + 이미지 넣기
		for (Product product : productList) {
			MenuCard menuCard = new MenuCard();
			
			// 상품 목록
			menuCard.setProduct(product);
			
			// 이미지 목록 가져오기
			int productId = product.getId();
			List<ProductImage> productImage = productBO.getProductImageByProductId(productId);
			
			// 그 상품의 이미지 목록
			menuCard.setProductImage(productImage);
			
			// 리스트에 추가
			menuCardList.add(menuCard);
		}
		
		return menuCardList;
	}
	
	// input: String		output:List<MenuCard>
	// 가공하는 view를 사용할 때는 generate
	public List<MenuCard> generateMenuCardListByCategory(String category){
		
		List<MenuCard> menuCardList = new ArrayList<>();
		
		// 상품 목록 가져오기
		List<Product> productList = productBO.getProductListByCategory(category);
		
		// 상품 + 이미지 넣기
		for (Product product : productList) {
			MenuCard menuCard = new MenuCard();
			
			// 상품 목록
			menuCard.setProduct(product);
			
			// 이미지 목록 가져오기
			int productId = product.getId();
			List<ProductImage> productImage = productBO.getProductImageByProductId(productId);
			
			// 그 상품의 이미지 목록
			menuCard.setProductImage(productImage);
			
			// 리스트에 추가
			menuCardList.add(menuCard);
		}
		
		return menuCardList;
	}
	
	// input: String		output:List<MenuCard>
	// 가공하는 view를 사용할 때는 generate
	public List<MenuCard> generateMenuCardListOrderByCreated(){
		
		List<MenuCard> menuCardList = new ArrayList<>();
		
		// 상품 목록 가져오기
		List<Product> productList = productBO.getProductListOrderByCreated();
		
		// 상품 + 이미지 넣기
		for (Product product : productList) {
			MenuCard menuCard = new MenuCard();
			
			// 상품 목록
			menuCard.setProduct(product);
			
			// 이미지 목록 가져오기
			int productId = product.getId();
			List<ProductImage> productImage = productBO.getProductImageByProductId(productId);
			
			// 그 상품의 이미지 목록
			menuCard.setProductImage(productImage);
			
			// 리스트에 추가
			menuCardList.add(menuCard);
		}
		
		return menuCardList;
	}
	
	// input: String		output:List<MenuCard>
	// 가공하는 view를 사용할 때는 generate
	public List<MenuCard> generateMenuCardListOrderByBuyCount(){
		
		List<MenuCard> menuCardList = new ArrayList<>();
		
		// 상품 목록 가져오기
		List<Product> productList = productBO.getProductListOrderByBuyCount();
		
		// 상품 + 이미지 넣기
		for (Product product : productList) {
			MenuCard menuCard = new MenuCard();
			
			// 상품 목록
			menuCard.setProduct(product);
			
			// 이미지 목록 가져오기
			int productId = product.getId();
			List<ProductImage> productImage = productBO.getProductImageByProductId(productId);
			
			// 그 상품의 이미지 목록
			menuCard.setProductImage(productImage);
			
			// 리스트에 추가
			menuCardList.add(menuCard);
		}
		
		return menuCardList;
	}
	
	
	// input: int		output:MenuCard
	// 가공하는 view를 사용할 때는 generate
	public MenuCard generateMenuCardById(int productId){
		
		// 상품 목록 가져오기
		Product product = productBO.getProductById(productId);
		
		MenuCard menuCard = new MenuCard();
			
		// 상품 목록
		menuCard.setProduct(product);
		
		List<ProductImage> productImage = productBO.getProductImageByProductId(productId);
		
		// 그 상품의 이미지 목록
		menuCard.setProductImage(productImage);
		
		return menuCard;
	}
	
	// input: int		output:MenuCard
	// 가공하는 view를 사용할 때는 generate
	public MenuCard generateMenuCardByProductId(int productId){
		
		// 상품 목록 가져오기
		Product product = productBO.getProductById(productId);
		
		MenuCard menuCard = new MenuCard();
		
		// 상품 목록
		menuCard.setProduct(product);
		
		List<ProductImage> productImage = productBO.getProductImageByProductId(productId);
		
		// 그 상품의 이미지 목록
		menuCard.setProductImage(productImage);
		
		return menuCard;
	}
	
	// input: int		output:MenuCard
	// 가공하는 view를 사용할 때는 generate
	public List<MenuCard> generateMenuCardListByProductId(int[] product, int userId){
		
		List<MenuCard> menu = new ArrayList<>();
		
		for (int productId : product) {
			// 상품 목록 가져오기
			Product productOne = productBO.getProductById(productId);
			
			MenuCard menuCard = new MenuCard();
			
			// 상품 목록
			menuCard.setProduct(productOne);
			
			List<ProductImage> productImage = productBO.getProductImageByProductId(productId);
			
			// 그 상품의 이미지 목록
			menuCard.setProductImage(productImage);
			
			// 장바구니 개수
			CartEntity cart = cartBO.getCartEntityByProductIdAndUserId(productId, userId);
			
			menuCard.setCart(cart);
			menu.add(menuCard);
		}
		
		return menu;
	}

}
