package com.slackathon.whatifollow1.controller;

import java.io.IOException;

import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.slack.api.model.event.Event;
import com.slack.api.model.event.MessageEvent;
import com.slackathon.whatifollow1.models.EventJson;
import com.slackathon.whatifollow1.models.FollowerJSON;
import com.slackathon.whatifollow1.models.FollowersResponse;
import com.slackathon.whatifollow1.models.InteractResponse;
import com.slackathon.whatifollow1.service.FollowersService;

@RestController
public class FollowersController {
	
	@Autowired
	private FollowersService followersService;
	
	@RequestMapping(value = "/addFollower", 
		    method = RequestMethod.POST)
	
	public ResponseEntity<String> addFollower(@RequestBody FollowerJSON requestJson)
	{
		ResponseEntity<String> response = null;
		try
		{
			followersService.insertFollower(requestJson.getUserId(), requestJson.getFollowerId());
			response = new ResponseEntity<String>("Follower added successfully", HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			response = new ResponseEntity<String>("Could not add follower", HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@RequestMapping(value = "/getFollowersList", method = RequestMethod.GET)
	public ResponseEntity<FollowersResponse> getFollowers(@RequestParam("userId") String userId)
	{
		ResponseEntity<FollowersResponse> responseEntity = null;
		try
		{
			FollowersResponse response = followersService.getFollowers(userId);
			responseEntity = new ResponseEntity<FollowersResponse>(response,  HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			responseEntity = new ResponseEntity<FollowersResponse>(HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
		
	}
	
	
	@RequestMapping(value = "/unFollowUser", method = RequestMethod.DELETE)
	public ResponseEntity<String> unFollowUser(@RequestBody FollowerJSON requestJson)
	{
		ResponseEntity<String> responseEntity = null;
		try
		{
			followersService.unFollowUser(requestJson.getUserId(), requestJson.getFollowerId());
			responseEntity = new ResponseEntity<String>("follow action deleted successfully",  HttpStatus.OK);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			responseEntity = new ResponseEntity<String>("could not unfollow the user", HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
		
	}
	
	@RequestMapping(value = "/slack/events", method = RequestMethod.POST)
	public ResponseEntity<String> slackEventNotifier(@RequestBody EventJson eventJson)
	{
		ResponseEntity<String> responseEntity = null;
		try
		{
			System.out.println(eventJson.getApi_app_id());
			
			if(eventJson.getChallenge()!=null && eventJson.getType().equalsIgnoreCase("url_verification"))
			{
				responseEntity = new ResponseEntity<String>(eventJson.getChallenge(), HttpStatus.OK);
			}
			else {
				if(eventJson.getEvent().getType().equalsIgnoreCase("app_home_opened")) {
					followersService.setChannelId(eventJson.getEvent().getChannel());
					followersService.setHomePage(eventJson.getEvent().getUser(),null,null);
					responseEntity = new ResponseEntity<String>("channel Id extracted successfully", HttpStatus.OK);
				}
				else {
					//the remaining method is to send the message to the above channel
					if(eventJson.getEvent().getType().equalsIgnoreCase("message")) {
						ObjectMapper objectMapper = new ObjectMapper();
						String jsonStr = objectMapper.writeValueAsString(eventJson);
						System.out.println(jsonStr);
						followersService.messageToChannel(eventJson);
					}
					
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			responseEntity = new ResponseEntity<String>("could not unfollow the user", HttpStatus.BAD_REQUEST);
		}
		
		System.out.println(responseEntity);
		return responseEntity;
		
	}
	
	
	
	@RequestMapping(value = "/interactive", method = RequestMethod.POST)
	public void slackInteractNotifier(@RequestBody InteractResponse interactResponse) throws ParseException, IOException
	{
		String actionType = interactResponse.getActions().get(0).getType();
		if(actionType.equalsIgnoreCase("users_select"))
		{
			followersService.setHomePage(interactResponse.getUser().getId(), interactResponse.getType(), interactResponse.getActions().get(0).getSelected_user());
		}
		
		if(actionType.equalsIgnoreCase("button"))
		{
			if(interactResponse.getActions().get(0).getText().getText().equalsIgnoreCase("Follow"))
			{
				followersService.insertFollower(interactResponse.getUser().getId(), interactResponse.getActions().get(0).getValue());
				followersService.setHomePage(interactResponse.getUser().getId(),null,null);
			}
			if(interactResponse.getActions().get(0).getText().getText().equalsIgnoreCase("UnFollow"))
			{
				followersService.unFollowUser(interactResponse.getUser().getId(), interactResponse.getActions().get(0).getValue());
				followersService.setHomePage(interactResponse.getUser().getId(),null,null);
			}
		}
		
	}
	
	

}
