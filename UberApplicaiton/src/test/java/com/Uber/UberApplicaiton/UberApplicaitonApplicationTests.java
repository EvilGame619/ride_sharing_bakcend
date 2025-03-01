package com.Uber.UberApplicaiton;

import com.Uber.UberApplicaiton.entities.User;
import com.Uber.UberApplicaiton.services.EmailSendService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class UberApplicaitonApplicationTests {

	@Autowired
	private EmailSendService emailSendService;

	@Test
	void contextLoads() {
	}

	@Test
	void testLombok(){
		User u = new User();
		u.setEmail("heor@gmail.com");
		System.out.println(u.getEmail());
	}

	@Test
	void sendEmail() {
		String[] email = {
		"sipox49203@apklamp.com",
				"singhashish070603@gmail.com",
				"everyonesdady@gmail.com"
	};
		emailSendService.sendEmail(
				email,
				"Testing mail",
				"this is the body of testing mail"
		);
	}



}
