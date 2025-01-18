package com.Uber.UberApplicaiton;

import com.Uber.UberApplicaiton.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class UberApplicaitonApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void testLombok(){
		User u = new User();
		u.setEmail("heor@gmail.com");
		System.out.println(u.getEmail());
	}



}
