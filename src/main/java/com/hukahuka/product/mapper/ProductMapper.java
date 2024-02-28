package com.hukahuka.product.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hukahuka.product.domain.Product;
import com.hukahuka.product.domain.ProductImage;

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
	
	// input: String		output: List<Product>
	public List<Product> selectProductListByCategory(String category);
	
	// input: count		output: X
	public void updateProductByCount(
			@Param("count") int count, 
			@Param("name") String name);
	
	// input: productId		output: X
	public void deleteProductManagerById(int id);
	
	// input: productId
	public void deleteProductManagerByProductId(int productId);
	
	// input: productId		output: List<Prodcut>
	public List<ProductImage> selectProductImageByProductId(int productId);
	
	// input: int 		output: Product
	public Product selectProductById(int productId);
	
	// input: X		output: List<Product>
	public List<Product> selectProductListOrderByCreated();
	
	// input: count		output: X
	public void updateBuyCount(int[] productId);

}
