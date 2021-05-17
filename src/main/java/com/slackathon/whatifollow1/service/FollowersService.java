package com.slackathon.whatifollow1.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.slack.api.methods.response.chat.ChatGetPermalinkResponse;
import com.slack.api.model.block.ActionsBlock;
import com.slack.api.model.block.ContextBlock;
import com.slack.api.model.block.ContextBlockElement;
import com.slack.api.model.block.DividerBlock;
import com.slack.api.model.block.HeaderBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.RichTextBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.block.element.ButtonElement;
import com.slack.api.model.block.element.ImageElement;
import com.slack.api.model.block.element.RichTextElement;
import com.slack.api.model.block.element.RichTextSectionElement;
import com.slack.api.model.block.element.RichTextSectionElement.Broadcast;
import com.slack.api.model.block.element.RichTextSectionElement.Channel;
import com.slack.api.model.block.element.RichTextSectionElement.Color;
import com.slack.api.model.block.element.RichTextSectionElement.Date;
import com.slack.api.model.block.element.RichTextSectionElement.Emoji;
import com.slack.api.model.block.element.RichTextSectionElement.Link;
import com.slack.api.model.block.element.RichTextSectionElement.Team;
import com.slack.api.model.block.element.RichTextSectionElement.Text;
import com.slack.api.model.block.element.RichTextSectionElement.User;
import com.slack.api.model.view.View;
import com.slackathon.whatifollow1.Utils.WhatIFollowUtils;
import com.slackathon.whatifollow1.constants.WhatIFollowContants;
import com.slackathon.whatifollow1.entities.Followers;
import com.slackathon.whatifollow1.models.Blocks;
import com.slackathon.whatifollow1.models.ConversationsInfoResponse;
import com.slackathon.whatifollow1.models.Elements;
import com.slackathon.whatifollow1.models.EventJson;
import com.slackathon.whatifollow1.models.FollowersResponse;
import com.slackathon.whatifollow1.models.UserInfoResponse;
import com.slackathon.whatifollow1.models.ViewPublishRequestJsonBuilder;
import com.slackathon.whatifollow1.repository.FollowersRepository;
@Service
public class FollowersService {
	
	@Value("${whatIFollow.webhookurl}") 
	private String webhookURL;
	
	@Value("${whatIFollow.token}")
	private String token;
	
	@Autowired
	private FollowersRepository followersRepo;
	
	public int insertFollower(String userId, String followerId)
	{
		Followers followers =new Followers(); ;
		
		
		com.slackathon.whatifollow1.models.User user = getUserDetails(followerId);
		
		followers.setName(user.getName());
		followers.setFolloweById(followerId);
		followers.setCreatedDate(new Date().toString());
		followers.setImageUrl(user.getProfile().getImageOriginal());
		followers.setTitle(user.getProfile().getTitle());
		followers.setUserId(userId);
		
		Followers savedFollower = followersRepo.save(followers);
			return savedFollower.getId();
	}
	
	
	public FollowersResponse getFollowers(String userId)
	{
		List<Followers> followers =  followersRepo.findFollowersForUser(userId);
		FollowersResponse followersResponse = new FollowersResponse();
		followersResponse.setUserId(userId);
		followersResponse.setFollowersList(followers);
		
		return followersResponse;
	}
	
	
	public void unFollowUser(String userId, String followerId)
	{
		List<Followers> followers = followersRepo.findIdByUserIdAndFollowerId(userId, followerId);
		if(followers.size()>0)
		{
			followersRepo.delete(followers.get(0));
		}
		
	}


	public void setChannelId(String channel) {
		// TODO Auto-generated method stub
		
		WhatIFollowContants.appChannelId= channel;
		
	}
	
	public void messageToChannel(EventJson eventJson)
	{
		String json = constructBlockFromRequest(eventJson);
		ResponseEntity<String> response = WhatIFollowUtils.sendRequest(token,webhookURL,String.class,HttpMethod.POST,json);
		
		System.out.println(response);
		
	}


	private String constructBlockFromRequest(EventJson eventJson) {
		// TODO Auto-generated method stub
		com.slackathon.whatifollow1.models.User user = getUserDetails(eventJson.getEvent().getUser());
		String userName = user.getName();
		String channelName = getChannelDetails(eventJson.getEvent().getChannel());
		String permaLink = getPermaLink(eventJson.getEvent().getChannel(), eventJson.getEvent().getTs());
		
		List<LayoutBlock> blocks = new ArrayList<>();
		List<LayoutBlock> headerblocks = buildHeaderBlocks(permaLink,userName,channelName );
		blocks.addAll(headerblocks);
		blocks.addAll(getLayoutBlocksFromRequest(eventJson));
		
		String blockJson = WhatIFollowUtils.convertObjectToJson(blocks);
		String json = "{"+
				"\"blocks\" : "+blockJson+
		"}";
		
		System.out.println("blockJson is"+json);
		
		return json;
	}




	private Collection<? extends LayoutBlock> getLayoutBlocksFromRequest(EventJson eventJson) {
		// TODO Auto-generated method stub
		List<LayoutBlock> blocks = new ArrayList<>();
		for(Blocks b : eventJson.getEvent().getBlocks())
		{
			if(b.getType().equalsIgnoreCase("rich_text"))
			{
				List<BlockElement> elements = new ArrayList<>();
				for(Elements e: b.getElements())
				{
					if(e.getType().equalsIgnoreCase("rich_text_section"))
					{
						List<RichTextElement> richTextElements = new ArrayList<>();
						for(Elements ele:e.getElements())
						{
							if(ele.getType().equalsIgnoreCase("text"))
							{
								Text text = new RichTextSectionElement.Text(ele.getText(),null);
								richTextElements.add(text);
							}
							if(ele.getType().equalsIgnoreCase("emoji"))
							{
								Emoji emoji = new RichTextSectionElement.Emoji(ele.getName(),null,null);
								richTextElements.add(emoji);
							}
							if(ele.getType().equalsIgnoreCase("channel"))
							{
								 Channel channel = new RichTextSectionElement.Channel(ele.getChannelId(),null);
								 richTextElements.add(channel);
							}
							if(ele.getType().equalsIgnoreCase("user"))
							{
								User user = new RichTextSectionElement.User(ele.getUser_id(),null);
								richTextElements.add(user);
							}
							if(ele.getType().equalsIgnoreCase("link"))
							{
								Link link = new RichTextSectionElement.Link(ele.getUrl(),ele.getText(),null);
								richTextElements.add(link);
							}
							if(ele.getType().equalsIgnoreCase("team"))
							{
								Team team = new RichTextSectionElement.Team(ele.getTeamId(),null);
								richTextElements.add(team);
							}
							if(ele.getType().equalsIgnoreCase("date"))
							{
								Date date = new RichTextSectionElement.Date(ele.getTimestamp());
								richTextElements.add(date);
							}
							if(ele.getType().equalsIgnoreCase("broadcast"))
							{
								Broadcast broadcast = new RichTextSectionElement.Broadcast(ele.getRange());
								richTextElements.add(broadcast);
							}
							if(ele.getType().equalsIgnoreCase("color"))
							{
								Color color = new RichTextSectionElement.Color(ele.getValue());
								richTextElements.add(color);
							}
						}
						BlockElement element = RichTextSectionElement.builder().elements(richTextElements).build();
						elements.add(element);
						
					}
				}
				blocks.add(RichTextBlock.builder().elements(elements).build());
			}
		}
		
		return blocks;
	}


	private List<LayoutBlock> buildHeaderBlocks(String permaLink, String userName, String channelName) {
		// TODO Auto-generated method stub
		
		List<LayoutBlock> blocks = new ArrayList<LayoutBlock>();
		
		blocks.add(SectionBlock.builder().text(MarkdownTextObject.builder().text("<"+permaLink+"|@"+userName+" has posted a message in #"+channelName+">").build()).build());
		
		DividerBlock dividerBlock = DividerBlock.builder().build();
		blocks.add(dividerBlock);
		
		return blocks;
	}


	private String getPermaLink(String channel, String ts) {
		// TODO Auto-generated method stub
		
		String url = WhatIFollowContants.permaLinkURL+"?channel="+channel+"&message_ts="+ts;
		
		ResponseEntity<ChatGetPermalinkResponse> response = WhatIFollowUtils.sendRequest(token, url, ChatGetPermalinkResponse.class,  HttpMethod.GET,null);
		
		return  response.getBody().getPermalink();
	}


	private String getChannelDetails(String channel) {
		// TODO Auto-generated method stub
		
		String url = WhatIFollowContants.channelInfoMethodURL+"?channel="+channel+"&pretty=1";
		
		ResponseEntity<ConversationsInfoResponse> response = WhatIFollowUtils.sendRequest(token, url, ConversationsInfoResponse.class,  HttpMethod.GET,null);
		
		return  response.getBody().getChannel().getName();
	}


	private com.slackathon.whatifollow1.models.User getUserDetails(String user) {
		// TODO Auto-generated method stub
		
		String url = WhatIFollowContants.userInfoMethodURL+"?user="+user+"&pretty=1";
		
		ResponseEntity<UserInfoResponse> response = WhatIFollowUtils.sendRequest(token, url, UserInfoResponse.class,  HttpMethod.GET,null);
		if(response.getBody()!= null && response.getBody().getUser()!=null)
		{
			return response.getBody().getUser();
		}
		else 
			return null;
	}


	public void setHomePage(String user, String type, String followerId) throws ParseException, IOException {
		// TODO Auto-generated method stub
		
		View view = ViewPublishRequestJsonBuilder.getHomePageSetup();
		
		if(type!=null && type.equalsIgnoreCase("block_actions"))
		{
			boolean isFollower = checkIfFollowerExists(followerId, user);
			if(!isFollower)
			{
				List<LayoutBlock> blocks = getFollowButtonBlock(followerId);
				if(blocks.size()>0)
				{
					view.getBlocks().addAll(blocks);
			
				}
			}
		}
		FollowersResponse followersResponse= getFollowers(user);
		if(followersResponse.getFollowersList().size()>0)
		{
			List<LayoutBlock> blocks = getBlocksForUsers(followersResponse.getFollowersList());
			view.getBlocks().addAll(blocks);
		}
			
		
		String jsonPayload = jsonPayloadForViewPublish(user, view);
		
		String url = WhatIFollowContants.followAppHomeView;
		
		ResponseEntity<String> response = WhatIFollowUtils.sendRequest(token, url, String.class,  HttpMethod.POST,jsonPayload);
		System.out.println(response.getBody());
	}


	private boolean checkIfFollowerExists(String followerId, String user) {
		// TODO Auto-generated method stub
		
		List<Followers> followers = followersRepo.findIdByUserIdAndFollowerId(user, followerId);
		if(followers.size()>0)
		{
			return true;
		}
		else {
			return false;
		}
		
	}


	private List<LayoutBlock> getFollowButtonBlock(String followerId) {
		// TODO Auto-generated method stub
		
		
		List<LayoutBlock> blocks = new ArrayList();
			
			PlainTextObject textObject = new PlainTextObject("Follow", true);
			
			ButtonElement buttonElement = ButtonElement.builder().text(textObject).build();
			buttonElement.setValue(followerId);
			//buttonElement.setActionId(followerId);
			List<BlockElement> newList = new ArrayList<>();
			newList.add(buttonElement);
			
			SectionBlock sectionBlock = SectionBlock.builder().text(MarkdownTextObject.builder().text(" ").build())
			.accessory(buttonElement).build();
			
			blocks.add(sectionBlock);
		
		return blocks;
		
	}
	
	


	private  String jsonPayloadForViewPublish(String user, View view) {
		// TODO Auto-generated method stub
		
		String viewJson = WhatIFollowUtils.convertObjectToJson(view);
		
		String json = "{"+
				"\"user_id\" : "+"\""+user+"\","+
				"\"view\" :"+ viewJson+
		"}";
		json = json.replaceAll("\"imageUrl\"", "\"image_url\"");
		json = json.replaceAll("\"altText\"", "\"alt_text\"");
		return json;
	}


	private List<LayoutBlock> getBlocksForUsers(List<Followers> followersList) {
		// TODO Auto-generated method stub
		
		List<LayoutBlock> blocks = new ArrayList();
		
		PlainTextObject textObject = new PlainTextObject("People you Follow", true);
		
		blocks.add(HeaderBlock.builder().text(textObject).build());
		
		
		
		for(Followers follower : followersList)
		{
			DividerBlock dividerBlock = DividerBlock.builder().build();
			blocks.add(dividerBlock);
			
			ImageElement imageElement = new ImageElement();
			imageElement.setImageUrl(follower.getImageUrl());
			imageElement.setAltText(follower.getName());
			
			SectionBlock sectionBlock = SectionBlock.builder().text(MarkdownTextObject.builder().text(follower.getName()).build())
					.accessory(imageElement).build();
			
			blocks.add(sectionBlock);
			
			MarkdownTextObject markdownTextObject =  MarkdownTextObject.builder().text("Following since "+ follower.getCreatedDate()).build();
			List<ContextBlockElement> contextBlockElements = new ArrayList<>();
			contextBlockElements.add(markdownTextObject);
			ContextBlock contextBlock = ContextBlock.builder().elements(contextBlockElements).build();
			blocks.add(contextBlock);
			
			PlainTextObject unFollowButton = new PlainTextObject("UnFollow", true);
			
			ButtonElement unFollowbuttonElement = ButtonElement.builder().text(unFollowButton).build();
			unFollowbuttonElement.setValue(follower.getFolloweById());
			unFollowbuttonElement.setStyle("danger");
			
			PlainTextObject viewDetailButton = new PlainTextObject("View Details", true);
			ButtonElement viewDetailbuttonElement = ButtonElement.builder().text(viewDetailButton).build();
			viewDetailbuttonElement.setValue(follower.getFolloweById());
			
			List<BlockElement> newList = new ArrayList<>();
			newList.add(unFollowbuttonElement);
			newList.add(viewDetailbuttonElement);
			
			ActionsBlock actionsBlock = ActionsBlock.builder().elements(newList).build();
			
			blocks.add(actionsBlock);
		}
		
		return blocks;
	}
	
	private List<LayoutBlock> getBlocksForUsers_bkp(List<Followers> followersList) {
		// TODO Auto-generated method stub
		
		List<LayoutBlock> blocks = new ArrayList();
		for(Followers follower : followersList)
		{
			DividerBlock dividerBlock = DividerBlock.builder().build();
			blocks.add(dividerBlock);
			
			PlainTextObject textObject = new PlainTextObject("UnFollow", true);
			
			ButtonElement buttonElement = ButtonElement.builder().text(textObject).build();
			buttonElement.setValue(follower.getFolloweById());
			List<BlockElement> newList = new ArrayList<>();
			newList.add(buttonElement);
			
			SectionBlock sectionBlock = SectionBlock.builder().text(MarkdownTextObject.builder().text(follower.getName()).build())
			.accessory(buttonElement).build();
			
			blocks.add(sectionBlock);
		}
		
		return blocks;
	}
	

}
