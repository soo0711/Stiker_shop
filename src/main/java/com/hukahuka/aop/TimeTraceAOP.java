package com.hukahuka.aop;

import java.lang.ProcessHandle.Info;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Component // spring bean, singleton(싱글톤)
@Aspect // aop - aspect: 부가 기능 정의 (advice) + 어디에 적용(pointcut) / 용어 잘 기억
@Slf4j
public class TimeTraceAOP {

	// 1) 수행할 패키지 지정
	// @Around("execution(* com.hukahuka..*(..))") // 적용하는 패키지 검색해보기
	
	// 2) 어노테이션 지정
	@Around("@annotation(TimeTrace)") // 어느 어노테이션이 붙어있을 때 수행되는가? -> TimeTrace라는 어노테이션
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
		// 타겟이 적용하는 메소드 - joinPoint
		
		// 시간 측정
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		// 본래 할 일 수행 (예: 회원가입, 로그인, 글쓰기 등등)
		Object proceedObj = joinPoint.proceed();
		
		// 시간 종료
		stopWatch.stop();
		
		// 두가지 출력 방식
		log.info("################ 실행 시간(ms): {}", stopWatch.getTotalTimeMillis());
		log.info("$$$$$$$$$$$$$$$$ 실행 시간(ms)" + stopWatch.prettyPrint());
		
		return proceedObj;
	}
}
