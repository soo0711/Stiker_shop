package com.hukahuka.manager.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hukahuka.product.bo.ProductBO;

@Service
public class ManagerBO {

	@Autowired
	private ProductBO productBO;
	
	public void addProduct(String name, String count, String detailProduct, 
			String introduceProduct, String category, MultipartFile fileName) {
		String imgPath = null;
		
		// 이미지 저장
	}
}
