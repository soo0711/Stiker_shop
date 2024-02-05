package com.hukahuka.user.bo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class UserBOTest {

	@Autowired
	UserBO userBO;
	
	@Transactional // roll back 해주는 기능, springframewrok쪽으로 import
	@Test
	void 유저추가테스트() {
		log.info("######## 유저 추가 테스트 시작");
		userBO.addUser("test23333", "bbbb", "테스트11", "01000001211", "aa11@test.com");
	}

}
