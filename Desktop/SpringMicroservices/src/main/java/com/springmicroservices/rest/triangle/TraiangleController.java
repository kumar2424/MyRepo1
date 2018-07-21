package com.springmicroservices.rest.triangle;

import java.util.EmptyStackException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.writers.CacheControlHeadersWriter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springmicroservices.rest.exceptions.EmptyStringException;
import com.springmicroservices.rest.exceptions.NotAValidInputException;
import com.springmicroservices.rest.exceptions.StringContainsNumberException;

@RestController
public class TraiangleController {
	private static final Logger log = LoggerFactory.getLogger(TraiangleController.class);
	
	@Autowired
	private CheckTriangleService checkTriangleService;
	CacheControlHeadersWriter cacheControl=null;
	String triangleType=null;
	
	
	
	@RequestMapping(path="/api/TriangleType/a={side1}&b={side2}&c={side3}",method=RequestMethod.GET)
	public ResponseEntity<String> calculateFibonacci(@PathVariable("side1") int side1,@PathVariable("side2") int side2,@PathVariable("side3") int side3){
		
		cacheControl=new CacheControlHeadersWriter();
		if(!(side1+side2>side3)) {
			throw new NotAValidInputException("Triangle is not possible with sent inputs");
		}else {
			triangleType=checkTriangleService.checkTriangleType(side1,side2,side3);
		}
		
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).cacheControl(CacheControl.noCache()).body(triangleType);
		
	}
}
