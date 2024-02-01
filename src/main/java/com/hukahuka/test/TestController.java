package com.hukahuka.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hukahuka.wish.mapper.WishMapper;

@Controller
public class TestController {

	@Autowired
	private WishMapper wishMapper;
	
	@ResponseBody
	@RequestMapping("/test1")
	public String test1() {
		return "Hello World!";
	}
	
	@ResponseBody
	@RequestMapping("/test2")
	public Map<String, Object> test2(){
		Map<String, Object> result = new HashMap<>();
		result.put("aaa", 11);
		result.put("bbb", 11);
		result.put("ccc", 11);
		return result;
	}
	
	@RequestMapping("/test3")
	public String test3() {
		return "test/test";
	}
	
	@ResponseBody
	@RequestMapping("/test4")
	public List<Map<String, Object>> test4(){
		return wishMapper.selectWishList();
	}
	
}
