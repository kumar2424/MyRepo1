package com.springmicroservices.rest;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.minidev.json.parser.JSONParser;

@Service
public class CreateSingleArrayService {
	private static final Logger log = LoggerFactory.getLogger(CreateSingleArrayService.class);
	
	public static void main(String[] args) throws JSONException {
		String inputArray=" {\r\n" + 
				"	\"array1\":[1,2,3],\r\n" + 
				"	\"array2\":[1,2,3,4],\r\n" + 
				"	\"array3\":[1,2,3,6]\r\n" + 
				"}";
		
		//log.info("Input array is "+inputArray);
		
		JSONObject obj = new JSONObject();
		obj.put("array0", "[1,2,3,3,4]");
	      obj.put("array1", "[1,2,3]");
	      obj.put("array2", "[1,2,3,4]");
	      obj.put("array3", "[1,2,3,6]");
	      obj.put("array4", "[1,2,7,8]");
	      
	     // System.out.print("JSON ARRAY IS "+obj);
		CreateSingleArrayService cst=new CreateSingleArrayService();
		cst.createSingleArray(obj);
	}
	
	public String createSingleArray(JSONObject inputArray) throws JSONException {
		
		String singleArray=null;
		Set<Integer> hashset=null;
		
		
		//log.info("Inside service method "+ inputArray);
		
		try {
			
			int length=inputArray.length();
			log.info("Length os Array is  "+ length);
			
			hashset=new HashSet<>();
			
			for(int i=0;i<inputArray.length();i++) {
				
				String inputString = (String) inputArray.get("array"+i);
				
				//String jsonArray=inputString.substring(1,inputArray.length()+1);
				log.info("input string is "+inputString);
				String jsonArray=inputString.substring(inputString.indexOf("[")+1,inputString.indexOf("]"));
				log.info("jsonArray is "+jsonArray);
				String[] inputArrayData = jsonArray.split(",");
				
				for(int j=0;j<inputArrayData.length;j++) {
					
					System.out.println("Values are "+inputArrayData[j]);
					hashset.add(Integer.parseInt(inputArrayData[j]));
					System.out.println("Inside for loop J");
				}
				System.out.println("After for loop J");
				
			}
			log.info("Output Array is "+hashset);
			hashset.forEach((num)->System.out.println("Hasset value is "+num));
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		//
		return singleArray;
	}
}
