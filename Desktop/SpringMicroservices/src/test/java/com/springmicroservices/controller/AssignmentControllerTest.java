package com.springmicroservices.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.BDDMockito.given;

import com.springmicroservices.rest.controller.AssignmentController;
import com.springmicroservices.rest.service.FibonacciService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AssignmentController.class, secure = false)
public class AssignmentControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FibonacciService fibonacciService;

	/*Course mockCourse = new Course("Course1", "Spring", "10 Steps",
			Arrays.asList("Learn Maven", "Import Project", "First Example",
					"Second Example"));*/
	Integer response=55;
	//String exampleCourseJson = "{\"name\":\"Spring\",\"description\":\"10 Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";

	@Test
	public void calculateFibonacci() throws Exception {
		//given(assignmentController.calculateFibonacci(10)).willReturn(55);
		Mockito.when(fibonacciService.fibonacciCalculator(10)).thenReturn(response);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				"/api/Fibonacci/n=10").accept(
				MediaType.ALL);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "55";

		// {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
}
