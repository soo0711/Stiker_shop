package com.hukahuka.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hukahuka.order.bo.OrdersBO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component // 일반 spring bean
public class TestBatch {

	@Autowired
	private OrdersBO ordersBO;
	// job
	// @Scheduled(cron = "0 */1 * * * *") // '/'가 들어가면 매 1분마다 실행, 없으면 정시의 1분마다
	public void task() {
		log.info("######### batch 수행");
	}
	


}
