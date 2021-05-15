package com.slackathon.whatifollow1.models;

import java.util.ArrayList;
import java.util.List;

import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.model.block.element.UsersSelectElement;
import com.slack.api.model.view.View;

public class ViewPublishRequestJsonBuilder {
	
	public static View getHomePageSetup() {
		
		View view = new View();
		List<LayoutBlock> blocks = new ArrayList<LayoutBlock>();
		
		UsersSelectElement usersSelectElement = new UsersSelectElement();
		PlainTextObject plainTextObject = new PlainTextObject("Select a user", true);
		usersSelectElement.setPlaceholder(plainTextObject);
		
		blocks.add(SectionBlock.builder().text(MarkdownTextObject.builder().text("Select the user from the users List").build())
			.accessory(usersSelectElement).build());
		
		view.setType("home");
		view.setBlocks(blocks);
		
		
		
		return view;
	}

}
