package com.slackathon.whatifollow1.models;

import java.util.ArrayList;
import java.util.List;

import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.model.block.element.ButtonElement;
import com.slack.api.model.view.View;

public class ViewPublishRequestJsonBuilder {
	
	public static View getHomePageSetup() {
		
		View view = new View();
		List<LayoutBlock> blocks = new ArrayList<LayoutBlock>();
		
		ButtonElement buttonElement = new ButtonElement();
		PlainTextObject textObject = new PlainTextObject("Search for a User to Follow", true);
		buttonElement.setStyle("primary");
		buttonElement.setValue("SearchUser");
		buttonElement.setText(textObject);
		
		blocks.add(SectionBlock.builder().text(MarkdownTextObject.builder().text(":wave:*Welcome to SlackFeed!* \n To view any users public channels' messages, select any user and click Follow. ").build())
			.accessory(buttonElement).build());
		
		view.setType("home");
		view.setBlocks(blocks);
		
		
		
		return view;
	}

}
