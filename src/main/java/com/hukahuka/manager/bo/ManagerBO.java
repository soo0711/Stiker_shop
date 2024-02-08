package com.hukahuka.manager.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hukahuka.common.FileManagerService;
import com.hukahuka.product.bo.ProductBO;

@Service
public class ManagerBO {

	@Autowired
	private ProductBO productBO;
	
	@Autowired
	private FileManagerService fileManagerService;
	
	public void addProductManager(String userloginId, String name, int count, String detail, 
			String introduce, String category, MultipartFile fileName) {
		
		String imgPath = null;
		
		// 이미지 저장
		imgPath = fileManagerService.saveFile(imgPath, fileName);
		
		// db insert 
		productBO.addProduct(name, count, detail, introduce, category, imgPath);
	}
}
