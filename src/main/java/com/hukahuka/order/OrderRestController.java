package com.hukahuka.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hukahuka.order.bo.OrdersBO;
import com.hukahuka.order.bo.OrderProductBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/order")
public class OrderRestController {

	@Autowired
	private OrdersBO orderBO;

	
	@PostMapping("/order-list")
	public Map<String, Object> orderList(
				@RequestParam("name") String name,
				@RequestParam("postcode") int postcode,
				@RequestParam("totalAddress") String address, 
				@RequestParam("phoneNumber") String phoneNumber,
				@RequestParam("email") String email, 
				@RequestParam(name = "deilverMessage", required = false) String deilverMessage,
				@RequestParam("payMethod") String payMethod,
				@RequestParam("total") int total ,
				@RequestParam(value = "productId") int[] productId,
				@RequestParam(value = "count") int[] count,
				HttpSession session){
		int userId = (int) session.getAttribute("userId");
		
		Map<String, Object> result = new HashMap<>();
		
		// db insert - order
		int orderId = orderBO.addOrder(name, postcode, address, phoneNumber, email, deilverMessage, payMethod, total, userId);
		
		// db insert - order_product
		orderBO.addOrderProduct(orderId, productId, count);
		
		// 응답값
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
}
