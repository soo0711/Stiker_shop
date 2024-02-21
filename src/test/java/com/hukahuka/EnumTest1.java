package com.hukahuka;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class EnumTest1 {

	public enum CalcType{
		// 열거형 정의
		CALC_A(value -> value), // value가 들어오면 value로 return 하겠다. (생성자 같은 느낌)
		CALC_B(value -> value * 10), // 람다식 ->>> 함수
		CALC_C(value -> value * 3),
		CALC_ETC(value -> 0);
		
		// enum의 정의된 function
		private Function<Integer, Integer> expression;// integer로 input, integer로 output이다.
		
		// 생성자
		CalcType(Function<Integer, Integer> expression){
			this.expression = expression;
		}
		
		// 계산 적용 method
		public int calculate(int value) {
			return expression.apply(value); // 각자에 맞는 함수를 실행한다. (.apply)
		}
	}
	
	@Test
	void cal테스트() {
		// given - 준비
		CalcType calcType = CalcType.CALC_C;
		
		// when - 실행
		int result = calcType.calculate(500);
		
		// then  - 검증
		assertEquals(1500, result);
		
	}
}
