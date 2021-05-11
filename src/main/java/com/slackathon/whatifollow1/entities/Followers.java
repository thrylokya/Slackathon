package com.slackathon.whatifollow1.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries(  
	    {  
	        @NamedQuery(  
	        name = "Followers.findFollowersForUser",  
	        query = "SELECT f.followeById from Followers f where f.userId = :userId"  
	        ),
	        @NamedQuery(  
	    	        name = "Followers.findIdByUserIdAndFollowerId",  
	    	        query = "SELECT f from Followers f where f.userId = :userId and f.followeById = :followerId"  
	    	        )
	    }  
	) 

@Entity
public class Followers {
	
	@javax.persistence.Id
	@GeneratedValue
	private int Id;
	
	private String userId;
	private String followeById;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFolloweById() {
		return followeById;
	}
	public void setFolloweById(String followeById) {
		this.followeById = followeById;
	}

}
