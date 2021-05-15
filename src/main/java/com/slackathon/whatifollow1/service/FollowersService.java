package com.slackathon.whatifollow1.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.slack.api.model.block.Blocks;
import com.slack.api.model.block.DividerBlock;
import com.slack.api.model.block.LayoutBlock;
import com.slack.api.model.block.SectionBlock;
import com.slack.api.model.block.composition.MarkdownTextObject;
import com.slack.api.model.block.composition.PlainTextObject;
import com.slack.api.model.block.element.BlockElement;
import com.slack.api.model.block.element.ButtonElement;
import com.slack.api.model.view.View;
import com.slackathon.whatifollow1.Utils.WhatIFollowUtils;
import com.slackathon.whatifollow1.constants.WhatIFollowContants;
import com.slackathon.whatifollow1.entities.Followers;
import com.slackathon.whatifollow1.models.ConversationsInfoResponse;
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
		followers.setUserId(userId);
		followers.setName(getUserDetails(followerId));
		followers.setFolloweById(followerId);
	
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
		constructBlockFromRequest(eventJson);
		
		System.out.println(this.webhookURL);
		
	}


	private void constructBlockFromRequest(EventJson eventJson) {
		// TODO Auto-generated method stub
		
		String userName = getUserDetails(eventJson.getEvent().getUser());
		String channelName = getChannelDetails(eventJson.getEvent().getChannel());
		
		List<Blocks> blocks = new ArrayList<>();
		
		
	}


	private String getChannelDetails(String channel) {
		// TODO Auto-generated method stub
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		HttpEntity request = new HttpEntity(headers);
		ResponseEntity<ConversationsInfoResponse> response = restTemplate.exchange(
				"https://slack.com/api/conversations.info?channel="+channel+"&pretty=1",
		        HttpMethod.GET,
		        request,
		        ConversationsInfoResponse.class,
		        1
		);

		return  response.getBody().getChannel().getName();
	}


	private String getUserDetails(String user) {
		// TODO Auto-generated method stub
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		String url = WhatIFollowContants.userInfoMethodURL+"?user="+user+"&pretty=1";
		HttpEntity request = new HttpEntity(headers);
		ResponseEntity<UserInfoResponse> response = restTemplate.exchange(url,  HttpMethod.GET,
		        request,
		        UserInfoResponse.class,
		        1);
		
		return response.getBody().getUser().getName();
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
		List<LayoutBlock> blocks = getBlocksForUsers(followersResponse.getFollowersList());
		view.getBlocks().addAll(blocks);
		
		String jsonPayload = jsonPayloadForViewPublish(user, view);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer "+token);
		headers.set("Content-Type", "application/json");
		
		String url = WhatIFollowContants.followAppHomeView;
		HttpEntity request = new HttpEntity(jsonPayload, headers);
		
		ResponseEntity<String> response = WhatIFollowUtils.sendRequest(request, url, String.class, headers);
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
		
		return json;
	}


	private List<LayoutBlock> getBlocksForUsers(List<Followers> followersList) {
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
