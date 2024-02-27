package com.hukahuka.order.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.menuCard.bo.MenuCardBO;
import com.hukahuka.menuCard.domain.MenuCard;
import com.hukahuka.order.OrderRestController;
import com.hukahuka.order.entity.OrdersEntity;
import com.hukahuka.order.repository.OrdersRepository;

@Service
public class OrdersBO {
	
	@Autowired
	private OrderProductBO orderProductBO;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
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
	
	// input: params		output: int(orderId)
	public int addOrder(String name, int postcode, String address, String phoneNumber,
			String email, String deilverMessage, String payMethod, int total, int userId) {
		// order insert
		return ordersRepository.save(
			OrdersEntity.builder()
			.name(name)
			.postcode(postcode)
			.address(address)
			.phoneNumber(phoneNumber)
			.email(email)
			.deliveryMessage(deilverMessage)
			.payMethod(payMethod)
			.totalPay(total)
			.userId(userId)
			.status("배송 준비 중")
			.build()).getId();
	}
	
	// input: params		output: void
	public void addOrderProduct(int orderId, int[] productId, int[] count) {
		orderProductBO.addProductOrder(orderId, productId, count);;
	}
}
