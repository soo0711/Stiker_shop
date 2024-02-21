package com.hukahuka.wish;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hukahuka.wish.bo.WishBO;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/wish")
public class WishController {
	@Autowired
	private WishBO wishBO;

	@GetMapping("/wish-list-view")
	public String CartListView(
			Model model,
			HttpSession session) {
		// userId
		int userId = (int) session.getAttribute("userId");
		
		// db select - user의 cart, product
		List<Map<String, Object>> wishList = wishBO.getMenuCardListByWishAndProduct(userId);
		
		model.addAttribute("wishs", wishList);
		model.addAttribute("viewName", "wish/wishList");
		
		return "template/layout";
	}
}
