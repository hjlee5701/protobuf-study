package com.example.study;

import com.example.study.client.ClientController;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GrpcServerApplicationTests {

	private ClientController clientController;
	@Test
	void contextLoads() {
	}

//	@Test
//	void grpcServerTest() {
//		String message = (String)this.clientController.testGrpc("test").getBody();
//		System.out.println(message);
//	}

}
