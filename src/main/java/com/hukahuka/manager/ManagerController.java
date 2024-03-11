package com.hukahuka.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hukahuka.manager.bo.ManagerBO;
import com.hukahuka.order.entity.OrdersEntity;
import com.hukahuka.product.domain.Product;

@Controller
@RequestMapping("/admin")
public class ManagerController {
	
	@Autowired
	private ManagerBO managerBO;

	@GetMapping("/hukahuka-upload-view")
	public String hukahukaUploadView(
			Model model) {
		model.addAttribute("viewName", "admin/upload");
		return "template/layout";
	}
	
	@GetMapping("/upload-view")
	public String hukahukaUploadView(
			@RequestParam("menu") int menu
			, Model model) {
		if (menu == 1) { // 상품등록
			return "admin/upload";
		}
		
		if (menu == 2) { // 재고현황
			// db select
			List<Product> products = managerBO.getProductList();
			model.addAttribute("products", products);
			
			return "admin/storage";
		}
		
		if (menu == 3) { // 배송현황
			// db select
			List<OrdersEntity> orders = managerBO.getOrdersList();
			model.addAttribute("orders", orders);
			return "admin/delivery";
		}
		
		return "admin/upload";
	}
	
}
