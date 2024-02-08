package com.hukahuka.product.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hukahuka.product.entity.ProductEntity;
import com.hukahuka.product.repository.ProductRepository;

@Service
public class ProductBO {

	@Autowired
	private ProductRepository productRepository;
	
	// input: params	output: X
	public void addProduct(String name, int count, String detail, 
			String introduce, String category, String imagePath) {
		 productRepository.save(
				ProductEntity.builder()
				.name(name)
				.count(count)
				.detail(detail)
				.introduce(introduce)
				.category(category)
				.imagePath(imagePath)
				.build()
				);
	}
}
