package com.hukahuka;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class HukahukaApplicationTests {

//	@Test
//	void contextLoads() {
//	}

	@Test
	void 더하기테스트() {
		log.info("$$$$$ 더하기 테스트 시작");
		int a =10;
		int b = 20;
		assertEquals(30, a + b); // 왼쪽 바가 초록색이면 성공, 빨간색이면 실패
	}
}
