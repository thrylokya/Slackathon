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
import com.slack.api.model.block.ActionsBlock;
import com.slack.api.model.block.ContextBlock;
import com.slack.api.model.block.ContextBlockElement;
import com.slack.api.model.block.DividerBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.block.element.ButtonElement;
import com.slack.api.model.block.element.ImageElement;
import com.slack.api.model.view.View;
import com.slackathon.whatifollow1.Utils.WhatIFollowUtils;
import com.slackathon.whatifollow1.constants.WhatIFollowContants;

public class SpringRestClient {

	public static void main(String[] args) throws ParseException, IOException {
		
		parseViewPubJson();
	
		
	}

	private static void parseViewPubJson() throws JsonParseException, JsonMappingException, IOException {
		
		View view = new View();
		List<LayoutBlock> blocks = new ArrayList<LayoutBlock>();
		
		PlainTextObject textObject = new PlainTextObject("Search for a User to Follow", true);
		
		ButtonElement buttonElement = ButtonElement.builder().text(textObject).build();
		buttonElement.setValue("SearchUser");
		buttonElement.setStyle("primary");
		List<BlockElement> newList = new ArrayList<>();
		newList.add(buttonElement);
		
		
		blocks.add(SectionBlock.builder().text(MarkdownTextObject.builder().text(":wave:*Welcome to SlackFeed!* \n To get automatic updates of any users public channels' messages, select an user and click Follow. ").build())
			.accessory(buttonElement).build());
		
		
		view.setType("home");
		
		
		DividerBlock dividerBlock = DividerBlock.builder().build();
		blocks.add(dividerBlock);
		
		ImageElement imageElement = new ImageElement();
		imageElement.setImageUrl("https://api.slack.com/img/blocks/bkb_template_images/profile_1.png");
		imageElement.setAltText("Sravan Gereddy");
		/*imageElement.setFallback(null);
		imageElement.setImageBytes(20);
		imageElement.setImageHeight(20);
		imageElement.setImageWidth(20);*/
		
		SectionBlock sectionBlock = SectionBlock.builder().text(MarkdownTextObject.builder().text("Sravan Gereddy").build())
				.accessory(imageElement).build();
		
		blocks.add(sectionBlock);
		
		MarkdownTextObject markdownTextObject =  MarkdownTextObject.builder().text("Following since 06/23/2021").build();
		List<ContextBlockElement> contextBlockElements = new ArrayList<>();
		contextBlockElements.add(markdownTextObject);
		ContextBlock contextBlock = ContextBlock.builder().elements(contextBlockElements).build();
		blocks.add(contextBlock);
		
		PlainTextObject unFollowButton = new PlainTextObject("UnFollow", true);
		
		ButtonElement unFollowbuttonElement = ButtonElement.builder().text(unFollowButton).build();
		unFollowbuttonElement.setValue("abc");
		unFollowbuttonElement.setStyle("danger");
		
		PlainTextObject viewDetailButton = new PlainTextObject("View Details", true);
		ButtonElement viewDetailbuttonElement = ButtonElement.builder().text(viewDetailButton).build();
		viewDetailbuttonElement.setValue("abc");
		
		List<BlockElement> newList1 = new ArrayList<>();
		newList1.add(unFollowbuttonElement);
		newList1.add(viewDetailbuttonElement);
		
		ActionsBlock actionsBlock = ActionsBlock.builder().elements(newList1).build();
		
		blocks.add(actionsBlock);
		
		view.setBlocks(blocks);
		
		String s = WhatIFollowUtils.convertObjectToJson(view);
		System.out.println(s);
		RestTemplate restTemplate = new RestTemplate();
		
		String json = "{"+
				"\"user_id\" : \"U01RCG5NEFN\","+
				"\"view\" :"+ s+
		"}";

		json = json.replaceAll("\"imageUrl\"", "\"image_url\"");
		json = json.replaceAll("\"altText\"", "\"alt_text\"");
		
		
		System.out.println(json);
		
		//ViewsPublishRequest publishRequest = new ViewsPublishRequest(s,)
		
		HttpHeaders headers = new HttpHeaders();
		
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
