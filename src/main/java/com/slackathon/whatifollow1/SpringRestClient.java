package com.slackathon.whatifollow1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.slack.api.model.block.DividerBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.block.element.ButtonElement;
import com.slack.api.model.view.View;
import com.slackathon.whatifollow1.Utils.WhatIFollowUtils;
import com.slackathon.whatifollow1.constants.WhatIFollowContants;
import com.slackathon.whatifollow1.models.ViewPublishRequestJsonBuilder;
import com.slackathon.whatifollow1.models.ViewPublishResponseJson;

public class SpringRestClient {

	public static void main(String[] args) throws ParseException, IOException {
		
		parseViewPubJson();
		
	}

	private static void parseViewPubJson() throws JsonParseException, JsonMappingException, IOException {
		
		View view = ViewPublishRequestJsonBuilder.getHomePageSetup();
		
		DividerBlock dividerBlock = DividerBlock.builder().build();
		view.getBlocks().add(dividerBlock);
		
		PlainTextObject textObject = new PlainTextObject("UnFollow", true);
		
		ButtonElement buttonElement = ButtonElement.builder().text(textObject).build();
		buttonElement.setValue("UnFollow");
		List<BlockElement> newList = new ArrayList<>();
		newList.add(buttonElement);
		
		SectionBlock sectionBlock = SectionBlock.builder().text(MarkdownTextObject.builder().text("User 1").build())
		.accessory(buttonElement).build();
		
		view.getBlocks().add(sectionBlock);
		
		
		PlainTextObject textObject2 = new PlainTextObject("UnFollow", true);
		
		ButtonElement buttonElement2 = ButtonElement.builder().text(textObject2).build();
		buttonElement.setValue("UnFollow");
		List<BlockElement> newList2 = new ArrayList<>();
		newList2.add(buttonElement2);
		
		SectionBlock sectionBlock2 = SectionBlock.builder().text(MarkdownTextObject.builder().text("User 1").build())
		.accessory(buttonElement).build();
		view.getBlocks().add(dividerBlock);
		view.getBlocks().add(sectionBlock2);
		
		String s = WhatIFollowUtils.convertObjectToJson(view);
		System.out.println(s);
		RestTemplate restTemplate = new RestTemplate();
		
		String json = "{"+
				"\"user_id\" : \"U01RCG5NEFN\","+
				"\"view\" :"+ s+
		"}";
		
		System.out.println(json);
		
		//ViewsPublishRequest publishRequest = new ViewsPublishRequest(s,)
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer xoxb-1856586297971-2057635471889-QmG5qxQTUN9eENk1X9ZHNFL9");
		headers.set("Content-Type", "application/json");
		String url = WhatIFollowContants.followAppHomeView;
		HttpEntity request = new HttpEntity(json, headers);
		System.out.println(request);
		ResponseEntity<String> response = restTemplate.exchange(url,  HttpMethod.POST,
		        request,
		        String.class,
		        1);
		System.out.println(response.toString());
		
	}
	
	
}
