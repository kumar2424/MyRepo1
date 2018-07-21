package com.springmicroservices.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.header.writers.CacheControlHeadersWriter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateSingleArrayController {
	private static final Logger log = LoggerFactory.getLogger(CreateSingleArrayController.class);
	
	@Autowired
	private CreateSingleArrayService createSingleArrayService;
	CacheControlHeadersWriter cacheControl=null;
	
	//@RequestMapping(value="/api/makeonearray",method=RequestMethod.GET)
	@RequestMapping(value="/api/makeonearray",method=RequestMethod.POST)	
	public ResponseEntity<String> makeOneArray(@RequestBody String requestInputString){
		String list="arrays";
		log.info("Inside Controller");
		
		log.info("Input JSON is "+list);
		//return list;
		return ResponseEntity.ok().body(list);
		//return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).cacheControl(CacheControl.noCache()).body(list);
		
	} 
	/*public ResponseEntity<String> createSingleArr( ){
		//List<Integer> list=null;
		log.info("Inside Controller");
		String list="arrays";
		log.info("Input JSON is "+list);
		//return list;
		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).cacheControl(CacheControl.noCache()).body(list);
		}*/
	
	
	
	}

