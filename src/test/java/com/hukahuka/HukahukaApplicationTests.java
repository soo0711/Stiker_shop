package com.hukahuka;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class HukahukaApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	//@Test
	void 더하기테스트() {
		log.info("$$$$$ 더하기 테스트 시작");
		int a =10;
		int b = 20;
		assertEquals(30, a + b); // 왼쪽 바가 초록색이면 성공, 빨간색이면 실패
	}
	
	@Test
	void 비어있는지확인() {
		// List<Integer> list = new ArrayList<>(); // []
		List<Integer> list = null; // []
		if (ObjectUtils.isEmpty(list)) { // null 이든 [] 이든 true가 된다.
			log.info("list is empty.");
		}
		
		// String str = null;
		String str = "";
		if (ObjectUtils.isEmpty(str)) { // null or empty일때 사용
			log.info("str is empty");
		}
	}
	

}
