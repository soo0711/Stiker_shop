package com.hukahuka.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hukahuka.product.domain.Product;

@Mapper
public interface ProductMapper {
	
	// input: params	output: Product
	public void insertProduct(Map<String, Object> map);
	
	
	// input: productId, imagePath		output: void
	public void insertProductImage(
			@Param("productId") int productId, 
			@Param("imagePath") List<String> imagePath);
	
	// input: X		output: List<Product>
	public List<Product> selectProductList();
	
	// input: count		output: X
	public void updateProductByCount(
			@Param("count") int count, 
			@Param("name") String name);
	
	// input: count		output: X
	public void deleteProductManager(String name);

}
