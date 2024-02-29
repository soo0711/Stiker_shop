package com.hukahuka.batch;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component // 일반 spring bean
public class OrderBatch {

		// job
		//@Scheduled(cron = "0 */1 * * * *") // '/'가 들어가면 매 1분마다 실행, 없으면 정시의 1분마다
		public void task() {
			log.info("######### order 지우기 수행");
		}
}
