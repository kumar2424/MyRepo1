package com.springmicroservices.rest.reverse;

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
import com.springmicroservices.rest.exceptions.StringContainsNumberException;

@RestController
public class ReverseStringController {
	private static final Logger log = LoggerFactory.getLogger(ReverseStringController.class);
	
	@Autowired
	private ReverseStringService reverseStringService;
	CacheControlHeadersWriter cacheControl=null;
	String regex="(.)*(\\d)(.)*";
	
	
	@RequestMapping(path="/api/ReverseWords/sentence={stringToBeReversed}",method=RequestMethod.GET)
	public ResponseEntity<String> reverseStr(@PathVariable("stringToBeReversed") String stringToBeReversed){
		Pattern pattern = Pattern.compile(regex);
		//String msg = "What is the square of 10?";
		boolean containsNumber = pattern.matcher(stringToBeReversed).matches();
		if(stringToBeReversed.equals(null)||stringToBeReversed.equals("")) {
			throw new EmptyStringException("Input String cannot be empty");
		}else if(containsNumber) {
			throw new StringContainsNumberException("Input String should not contain Numbers");
		}		
		else {
		String reversedString=reverseStringService.reverseString(stringToBeReversed);
		cacheControl=new CacheControlHeadersWriter();	
		
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).cacheControl(CacheControl.noCache()).body(reversedString);
		}
	}
}
