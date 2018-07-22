package com.springmicroservices.rest;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateSingleArrayController {
	private static final Logger log = LoggerFactory.getLogger(CreateSingleArrayController.class);

	@Autowired
	private CreateSingleArrayService createSingleArrayService;

	@RequestMapping(value = "/api/makeonearray", method = RequestMethod.POST)
	public ResponseEntity<String> makeOneArray(@RequestBody String inputArray) {
		String singleOutputArray = null;
		log.info("Inside Controller");

		singleOutputArray = createSingleArrayService.createSingleArray(inputArray);

		log.info("Input JSON is " + inputArray);

		return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
				.cacheControl(CacheControl.noCache()).body(singleOutputArray);

	}

}
