package com.hukahuka.order.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.cart.bo.CartBO;
import com.hukahuka.menuCard.bo.MenuCardBO;
import com.hukahuka.menuCard.domain.MenuCard;
import com.hukahuka.order.entity.OrdersEntity;
import com.hukahuka.order.repository.OrdersRepository;
import com.hukahuka.orderCard.bo.OrderCardBO;
import com.hukahuka.orderCard.domain.OrderCard;
import com.hukahuka.product.bo.ProductBO;
import com.hukahuka.user.bo.UserBO;

@Service
public class OrdersBO {
	@Autowired
	private OrderProductBO orderProductBO;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private MenuCardBO menuCardBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private ProductBO productBO;
	
	@Autowired
	private OrderCardBO orderCardBO;
	
	@Autowired
	private CartBO cartBO;

	// input: productId, count		output: MenuCard
	public MenuCard getMenuCard(int productId) {
		return menuCardBO.generateMenuCardById(productId);
	}
	
	// input:[] product, [] count		output: List<MenuCard>
	public List<MenuCard> getMenuCardList(int[] product, int userId) {
		return menuCardBO.generateMenuCardListByProductId(product, userId);
	}
	
	// input: params		output: int(orderId)
	public int addOrder(String name, int postcode, String address, String detailAddress,String phoneNumber,
			String email, String deilverMessage, String payMethod, int total, int userId) {
		// order insert
		if (payMethod.equals("card")) {
			return ordersRepository.save(
					OrdersEntity.builder()
					.name(name)
					.postcode(postcode)
					.address(address)
					.detailAddress(detailAddress)
					.phoneNumber(phoneNumber)
					.email(email)
					.deliveryMessage(deilverMessage)
					.payMethod(payMethod)
					.totalPay(total)
					.userId(userId)
					.status("배송 준비 중")
					.build()).getId();
		} else {
			return ordersRepository.save(
					OrdersEntity.builder()
					.name(name)
					.postcode(postcode)
					.address(address)
					.detailAddress(detailAddress)
					.phoneNumber(phoneNumber)
					.email(email)
					.deliveryMessage(deilverMessage)
					.payMethod(payMethod)
					.totalPay(total)
					.userId(userId)
					.status("입금 대기")
					.build()).getId();
		}
	}
	
	// input: params		output: void
	public void addOrderProduct(int orderId, int[] productId, int[] count) {
		orderProductBO.addProductOrder(orderId, productId, count);;
	}
	
	// input: check		output: void
	public void addUserAddress(String address, String detailAddress, int postcode, int userId) {
		userBO.addUserAddress(address, detailAddress, postcode, userId);
	}
	
	// input: X 	output: List<OrdersEntity>
	public List<OrdersEntity> getOrdersList(){
		return ordersRepository.findAll();
	}
	
	// input: X 	output: List<OrdersEntity>
	public List<OrdersEntity> getOrdersListByUserId(int userId){
		return ordersRepository.findByUserId(userId);
	}
	
	// input: X 	output: List<OrdersEntity>
	public List<OrdersEntity> getOrdersListByUserIdAndStatus(int userId, String status){
		return ordersRepository.findByUserIdAndStatus(userId, status);
	}
	
	// input: orderId, status		output: X
	public void updateOrdersByStatus(int orderId, String status) {
		OrdersEntity order = ordersRepository.findById(orderId).orElse(null);
		
		order = order.toBuilder()
				.status(status)
				.build();
		
		ordersRepository.save(order); // 데이터 있으면 수정
	}	
	
	// input: orderId		output: X
	public void deleteOrdersById(int orderId){
		OrdersEntity orders = ordersRepository.findById(orderId).orElse(null);
		if (orders != null) {
			ordersRepository.delete(orders);
		}
	}
	
	// input: productId		output: X
	public void updateCounts(int[] productId, int[] count) {
		productBO.updateCount(productId, count);
	}
	
	public OrderCard generateOrderCardList(int orderId){
		return orderCardBO.generateOrderCardList(orderId);
	}
	
	// input: productId		output: X
	public void deleteByProductId(int[] productId, int userId) {
		cartBO.deleteCartEntityByProductId(productId, userId);
	}
}
