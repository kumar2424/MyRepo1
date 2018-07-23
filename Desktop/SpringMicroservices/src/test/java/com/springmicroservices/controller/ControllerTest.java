package com.springmicroservices.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import com.springmicroservices.rest.SpringMicroservicesApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringMicroservicesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

	@org.springframework.boot.web.server.LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void testRetrieveStudentCourse() {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/students/Student1/courses/Course1"),
				HttpMethod.GET, entity, String.class);

		String expected = "110";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}
