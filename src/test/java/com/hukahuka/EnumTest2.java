package com.hukahuka;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import lombok.Getter;

public class EnumTest2 {

	@Getter // title을 꺼내는 역할 - ex) 무통장 입금
	enum PayMethod {
		REMITTANCE ("무통장입금")
		, ACCOUNT_TRANCEFER ("계좌이체")
		, CREDIT ("신용카드")
		, NAVER ("네이버페이");
		
		// 필드
		private String title;
		
		// 생성자
		PayMethod(String title){
			this.title = title;
		}
	}
	
	enum PayType {
		// 열거형 정의
		CASH("현금", List.of(PayMethod.REMITTANCE, PayMethod.ACCOUNT_TRANCEFER))
		, CARD("카드", List.of(PayMethod.CREDIT, PayMethod.NAVER))
		, EMPTY("없음", Collections.emptyList()); // 비어있는 리스트로 만들어진다. - 실무에서 많이 활용 Collections.EMPTY_LIST도 활용
		
		// 필드
		private String title;
		private List<PayMethod> payList;
		PayType(String title, List<PayMethod> payList){
			this.title = title;
			this.payList = payList;
		}
		
		// 결제 수단(리스트 안의 값) String이 enum이 존재하는지 확인
		public boolean hasPayMethod(PayMethod payMethod) {
			return payList.stream() // payList 순회
					.anyMatch(pay -> pay.equals(payMethod)); // 하나라도 같은게 있으면 true
		}
		
		// 결제 수단(String)으로 enum 타입(부모) 찾기 메소드, naver로 들어오면 card다 라는걸 찾는 것
		public static PayType findByPayMethod(PayMethod payMethod) {
			return Arrays.stream(PayType.values()) //PayType의 열거형 변수드을 stream으로 변환
					.filter(payType -> payType.hasPayMethod(payMethod)) // payMethod와 같은 type을 뽑아냄
					.findAny() // 찾은 요소 반환
					.orElse(EMPTY); // 없으면 PayType.EMPTY로 return 
		}

	}

	@Test
	void pay테스트2() {
		// given
		PayMethod payMethod = PayMethod.ACCOUNT_TRANCEFER;
		
		// when
		PayType payType = PayType.findByPayMethod(payMethod);
		
		// then
		assertEquals(payType, PayType.CASH);
		assertEquals("계좌이체", payMethod.getTitle());
		
	}
	
	
//  @Test
//	void pay테스트() {
//		// given 준비
//		String payMethod = "NAVER"; // 타입이 지정 안되었기때문에 static 메소드를 사용해야하낟.
//		
//		// when 
//		// 결제 수단에 해당하는 결제 종류는?
//		PayType payType = PayType.findByPayMethod(payMethod);
//		
//		// then 
//		assertEquals(payType, PayType.CARD);
//	}
}
