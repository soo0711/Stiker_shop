package com.hukahuka;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LambdaTest {

	// @Test
	void 람다식테스트1() {
		List<String> fruits = List.of("apple", "banana", "bherry");
		fruits
		.stream() // 계속 줄줄이 이어진다. (실제값 X, 감싸진 느낌 -> String X)
		.filter(fruit -> fruit.startsWith("b")) // 걸러낸다. 해당하는 것만 가져온다. (꺼내진 하나의 값)
		.forEach(fruit -> log.info("######### {}", fruit)); // for문
	}
	
	// @Test
	void 람다식테스트2() {
		List<String> fruits = List.of("apple", "banana", "bherry");
		fruits = fruits
		.stream()
		.map(fruit -> fruit.toUpperCase()) // 돌면서 대문자로 바꾼다. 
		.collect(Collectors.toList()); // 새로운 문자로 다시 저장 toList로 바꾸는 이유는 stream으로 저장 되어 있기 때문에 stream to list
		log.info(fruits.toString());
	}
	
	// @Test
	void 메소드레퍼런스() {
		List<String> fruits = List.of("apple", "banana", "bherry");
		fruits = fruits
				.stream()
				.map(String::toUpperCase) // 이름을 안 짓고 이미 요소가 있고 String에 toUpperCase를 적용하겠다. (각 요소에 메소드 적용)
				.collect(Collectors.toList()); // stream to List
		log.info(fruits.toString());
	}
	
	@Test
	void 람다_메소드레퍼런스() {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("전수현", 24));
		personList.add(new Person("김바다", 5));
		
		// 객체 안에 있는 메소드 호출
		personList.forEach(p -> p.printInfo()); // 람다 방식으로 호출
		personList.forEach(Person::printInfo); // 메소드 레퍼런스 호출
		
		// 객체를 println으로 출력
		personList.forEach(p -> System.out.println(p)); // 람다
		personList.forEach(System.out::println); // 메소드 레퍼런스
	} 
	
	
	@ToString // this 안에 있는 class를 보여준다.
	@AllArgsConstructor // 생성자
	class Person{
		private String name;
		private int age;
		
		public void printInfo() {
			log.info("###" + this); // this는 class 자체
		}
	}
}
