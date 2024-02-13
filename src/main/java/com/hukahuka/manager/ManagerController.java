package com.hukahuka.manager;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@RequestMapping("/hukahuka-upload-view")
	public String hukahukaUploadView(
			Model model) {
		model.addAttribute("viewName", "manager/upload");
		return "template/layout";
	}
	
	@RequestMapping("/upload-view")
	public String hukahukaUploadView() {
		return "manager/managerMenu";
	}
	
}
