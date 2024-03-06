package com.hukahuka.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hukahuka.order.bo.OrdersBO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component // 일반 spring bean
public class OrderBatch {
	
		@Autowired
		private OrdersBO ordersBO;

		// job
		@Scheduled(cron = "0 12 * * * 1") // 월요일 12시 00분 마다
		public void task() {
			log.info("######### 5년 전 order 지우기 수행");
			ordersBO.deleteOrderList();
		}
}
