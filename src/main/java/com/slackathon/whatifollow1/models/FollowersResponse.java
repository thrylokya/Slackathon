package com.slackathon.whatifollow1.models;

import java.util.List;

public class FollowersResponse {

	private String userId;
	private List<String> followersList;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<String> getFollowersList() {
		return followersList;
	}
	public void setFollowersList(List<String> followersList) {
		this.followersList = followersList;
	}
}
