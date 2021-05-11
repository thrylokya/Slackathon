package com.slackathon.whatifollow1.models;

import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class FollowerJSON {
	
	private String userId;
	private String followerId;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFollowerId() {
		return followerId;
	}
	public void setFollowerId(String followerId) {
		this.followerId = followerId;
	}

}
