package com.slackathon.whatifollow1.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.slack.api.methods.response.views.ViewsPublishResponse;
import com.slack.api.model.block.Blocks;
import com.slackathon.whatifollow1.constants.WhatIFollowContants;
import com.slackathon.whatifollow1.entities.Followers;
import com.slackathon.whatifollow1.models.ConversationsInfoResponse;
import com.slackathon.whatifollow1.models.EventJson;
import com.slackathon.whatifollow1.models.FollowersResponse;
import com.slackathon.whatifollow1.models.UserInfoResponse;
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
		followers.setFolloweById(followerId);
	
			Followers savedFollower = followersRepo.save(followers);
			return savedFollower.getId();
		
	}
	
	
	public FollowersResponse getFollowers(String userId)
	{
		List<String> followers =  followersRepo.findFollowersForUser(userId);
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
		String url = WhatIFollowContants.userInfoMethodURL+"?user="+user+"pretty=1";
		HttpEntity request = new HttpEntity(headers);
		ResponseEntity<UserInfoResponse> response = restTemplate.exchange(url,  HttpMethod.GET,
		        request,
		        UserInfoResponse.class,
		        1);
		
		return response.getBody().getUser().getName();
	}


	public void setHomePage(EventJson eventJson) throws ParseException, IOException {
		// TODO Auto-generated method stub
		
		File file = new ClassPathResource("staticModals/viewPublish.json").getFile();
		FileReader fileReader = new FileReader(file);
		JSONParser jsonParser = new JSONParser(fileReader);
		Object obj = jsonParser.parse();
		
        System.out.println(obj.toString());
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer xoxb-1856586297971-2057635471889-QmG5qxQTUN9eENk1X9ZHNFL9");
		String url = WhatIFollowContants.followAppHomeView+"?user=U01RCG5NEFN"+"pretty=1";
		HttpEntity request = new HttpEntity(obj.toString(), headers);
		ResponseEntity<ViewsPublishResponse> response = restTemplate.exchange(url,  HttpMethod.POST,
		        request,
		        ViewsPublishResponse.class,
		        1);
		System.out.println(response.toString());
		
	}

}
