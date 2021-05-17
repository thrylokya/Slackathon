package com.slackathon.whatifollow1.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WhatIFollowUtils {
	
	@SuppressWarnings("unchecked")
	public static <T> ResponseEntity<T> sendRequest(String token, String url, Class<T> className, HttpMethod httpMethod,String payload)
	{
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		headers.set("Content-Type", "application/json");
		
		HttpEntity<String> request = null;
		if(payload==null)
		{
			request=  new HttpEntity<String>(headers);
		}
		else {
			request = new HttpEntity(payload, headers);
		}
		
		ResponseEntity<T> response = restTemplate.exchange(url,  httpMethod,
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
