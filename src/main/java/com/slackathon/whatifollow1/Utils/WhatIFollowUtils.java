package com.slackathon.whatifollow1.Utils;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WhatIFollowUtils {
	
	public static <T> ResponseEntity<T> sendRequest(HttpEntity request,String url, Class<T> className,HttpHeaders headers)
	{
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<T> response = restTemplate.exchange(url,  HttpMethod.POST,
		        request,
		        className,
		        1);
		return response;
		
	}
	
	
	public static String convertObjectToJson(Object src)
	{
		Gson gson = new GsonBuilder().create();
		String jsonString = gson.toJson(src);
		return jsonString;
	}

}
