package com.slackathon.whatifollow1.models;

import java.util.List;

import com.slackathon.whatifollow1.entities.Followers;

public class FollowersResponse {

	private String userId;
	private List<Followers> followersList;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<Followers> getFollowersList() {
		return followersList;
	}
	public void setFollowersList(List<Followers> followersList) {
		this.followersList = followersList;
	}
}
