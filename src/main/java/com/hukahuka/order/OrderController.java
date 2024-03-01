package com.hukahuka.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hukahuka.menuCard.domain.MenuCard;
import com.hukahuka.order.bo.OrdersBO;
import com.hukahuka.order.entity.OrdersEntity;
import com.hukahuka.orderCard.domain.OrderCard;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrdersBO orderBO;


	@PostMapping("/order-view")
	public String orderListView(
		@RequestParam("productId") int productId,
		@RequestParam("count") int count,
		Model model) {
	
		// db select
		MenuCard menuCard = orderBO.getMenuCard(productId);
		List<MenuCard> menu = new ArrayList<>();
		menu.add(menuCard);
		
		model.addAttribute("cartOrderCard", menu);
		model.addAttribute("count", count);
		model.addAttribute("viewName", "order/orderList");
		
		return "template/layout";
	}
	
	@PostMapping("/order-list-view")
	public String orderView(
		@RequestParam("product") int[] product,
		HttpSession session,
		Model model) {
	
		int userId = (int) session.getAttribute("userId");
		
		// db select
		List<MenuCard> menuCard = orderBO.getMenuCardList(product, userId);
		
		model.addAttribute("cartOrderCard", menuCard);
		model.addAttribute("viewName", "order/orderList");
		
		return "template/layout";
	}
	
	@RequestMapping("/order-done")
	public String orderDone(
			Model model) {
		
		model.addAttribute("viewName", "order/done");
		return "template/layout";
	}

	@GetMapping("/list-view")
	public String ListView(
			@RequestParam(name = "status", required = false) String status,
			HttpSession session,
			Model model) {
		
		int userId = (int) session.getAttribute("userId");
		
		List<OrdersEntity> orderList = new ArrayList<>();
		
		if (status == null) {
			// db select
			orderList = orderBO.getOrdersListByUserId(userId);
			model.addAttribute("orderList", orderList);
			model.addAttribute("viewName", "order/list");
			return "template/layout";
		} else if (status.equals("all")) {
			orderList = orderBO.getOrdersListByUserId(userId);
			model.addAttribute("orderList", orderList);
			model.addAttribute("viewName", "order/list");
			return "order/list";
		} else {
			orderList = orderBO.getOrdersListByUserIdAndStatus(userId, status);
			model.addAttribute("orderList", orderList);
			model.addAttribute("viewName", "order/list");
			return "order/list";
		}
		
	}
	
	@PostMapping("/detail-list-view")
	public String DetailListView(
			@RequestParam("orderId") int orderId,
			HttpSession session,
			Model model) {
		
		// db select
		OrderCard orderCard = orderBO.generateOrderCardList(orderId);
		
		model.addAttribute("orderCardList", orderCard);
		model.addAttribute("viewName", "order/detailList");
		return "template/layout";
	}


}
