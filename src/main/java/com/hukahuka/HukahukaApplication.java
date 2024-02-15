package com.hukahuka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // Batch를 쓰겠다는 annotation
@SpringBootApplication
public class HukahukaApplication {

	public static void main(String[] args) {
		SpringApplication.run(HukahukaApplication.class, args);
	}

}
