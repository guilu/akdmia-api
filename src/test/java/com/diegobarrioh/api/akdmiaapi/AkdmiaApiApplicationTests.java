package com.diegobarrioh.api.akdmiaapi;

import com.diegobarrioh.api.akdmiaapi.security.xss.RequestWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;


@SpringBootTest
class AkdmiaApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void requestWrapperParameter() {
		MockHttpServletRequest req = new MockHttpServletRequest();
		String param1Name = "param1";
		String param2Name = "param2";
		String param1Value = "value";
		String param2Value = "javascript";
		req.setParameter(param1Name,param1Value);
		req.setParameter(param2Name,param2Value);
		HttpServletRequest request1 = new RequestWrapper(req);
		Assertions.assertEquals(param1Value, req.getParameter(param1Name));
		Assertions.assertEquals(param2Value, req.getParameter(param2Name));
		Assertions.assertEquals(param1Value, request1.getParameter(param1Name));
		Assertions.assertEquals(param2Value, request1.getParameter(param2Name));
	}

	@Test()
	void requestWrapperParameterFails() {
		MockHttpServletRequest req = new MockHttpServletRequest();
		String param1Name = "param1";
		String param2Name = "param2";
		String param1Value = "value";
		String param2Value = "<img src='img.png' onerror='alert('XSS')'/>";
		req.setParameter(param1Name,param1Value);
		req.setParameter(param2Name,param2Value);
		HttpServletRequest request1 = new RequestWrapper(req);
		Assertions.assertEquals(param1Value, req.getParameter(param1Name));
		Assertions.assertEquals(param2Value, req.getParameter(param2Name));
		Assertions.assertEquals(param1Value, request1.getParameter(param1Name));
		Assertions.assertThrows(IllegalArgumentException.class, () -> request1.getParameter(param2Name));
	}
}