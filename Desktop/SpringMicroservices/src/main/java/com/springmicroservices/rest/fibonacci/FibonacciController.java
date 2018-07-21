package com.springmicroservices.rest.fibonacci;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.writers.CacheControlHeadersWriter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.springmicroservices.rest.exceptions.NotAValidIndexException;

@RestController
public class FibonacciController {
	private static final Logger log = LoggerFactory.getLogger(FibonacciController.class);
	
	@Autowired
	private FibonacciService fibService;
	CacheControlHeadersWriter cacheControl=null;
	
	/*@RequestMapping(path="/api/Fibonacci/n={fibonacciIndex}",method=RequestMethod.GET)
	public ResponseEntity<Integer> calculateFibonacci(@PathVariable("fibonacciIndex") int fibNum){
		if(fibNum<0) {
			throw new NotAValidIndexException("Index should be positive for calculating Fibonacci number");
		}
		Handle Fibonacci number for large numbers
		else if(fibNum>40) {
			
		}
		else {
		Integer fibonaccinumber=fibService.fibonacciCalculator(fibNum);
		//return fibonaccinumber;
		return ResponseEntity.ok().cacheControl(CacheControl.noCache()).body(fibonaccinumber);
		}
	}*/
	
	@RequestMapping(path="/api/Fibonacci/n={fibonacciIndex}",method=RequestMethod.GET)
	public ResponseEntity<Integer> calculateFibonacci(@PathVariable("fibonacciIndex") int fibNum){
		
		cacheControl=new CacheControlHeadersWriter();
		
		if(fibNum<0) {
			throw new NotAValidIndexException("Index should be positive for calculating Fibonacci number");
		}
		/*Handle Fibonacci number for large numbers
		else if(fibNum>40) {
			
		}*/
		else {
		Integer fibonaccinumber=fibService.fibonacciCalculator(fibNum);
		//return fibonaccinumber;
		
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).cacheControl(CacheControl.noCache()).body(fibonaccinumber);
		}
	}
	
	
}
