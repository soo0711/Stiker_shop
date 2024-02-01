package com.hukahuka.wish.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WishMapper {

	// input: X		// output: X
	public List<Map<String, Object>> selectWishList();
}
