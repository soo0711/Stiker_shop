package com.hukahuka;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import lombok.Getter;

public class EnumTest {

	@Getter
	// 원래 Enum 이름 파일로 만든다
	public enum Status{ // Status.Y로 사용한다.
		// 열거형 정의
		Y(1, true), // Y면 1과 true로 볼 것이다. -> setter 역할
		N(0, false) // N면 0과 false으로 볼 것이다.
		;
		
		// enum에 정의된 항목의 필드
		private int value1; // 1이 여기 들어간다.
		private boolean value2; // ture가 여기 들어간다.
		
		// 생성자
		Status(int value1, boolean value2){
			this.value1 = value1;
			this.value2 = value2;
		}
	}
	
	@Test
	void status테스트() {
		// given - 준비
		Status status = Status.N;
		
		// when - 실행
		int value1 = status.getValue1();
		boolean value2 = status.isValue2(); // ★ boolean은 get말고 is로 꺼내야한다. ★
		
		//then - 검증
		assertEquals(status, Status.N); // 초록색은 Y가 맞다.
		assertEquals(0, value1); // 초록색은 맞다. 빨간색은 틀리다.
		assertEquals(false, value2);
			
	}
}
