package com.slackathon.whatifollow1.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NamedQueries(  
	    {  
	        @NamedQuery(  
	        name = "Followers.findFollowersForUser",  
	        query = "SELECT f from Followers f where f.userId = :userId"  
	        ),
	        @NamedQuery(  
	    	        name = "Followers.findIdByUserIdAndFollowerId",  
	    	        query = "SELECT f from Followers f where f.userId = :userId and f.followeById = :followerId"  
	    	        )
	    }  
	) 
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Followers {
	
	@javax.persistence.Id
	@GeneratedValue
	private int Id;
	
	private String userId;
	private String name;
	private String followeById;
	private String imageUrl;
	private String createdDate;
	private String title;
	
}
