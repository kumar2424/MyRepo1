package com.springmicroservices.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateSingleArrayService {
	private static final Logger log = LoggerFactory.getLogger(CreateSingleArrayService.class);

	public String reverseString(String stringToBeReversed) {
		String reversedString = null;
		String[] wordsInSentence = stringToBeReversed.split("[[ ]*|[//.]]");
		String reverseWord="";
		
		for (int i = 0; i < wordsInSentence.length; i++) {
			String wordInString=wordsInSentence[i];
			
			log.info("Word array is " + wordsInSentence[i]);
			
			for (int j = wordsInSentence.length - 1; j >= 0; j--) {
				reverseWord=reverseWord+wordInString.charAt(j);
				
			}
			reverseWord=reverseWord.concat(" ");
			
		}
		System.out.println(reverseWord);
		return reverseWord;

	}
}
