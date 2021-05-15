package com.slackathon.whatifollow1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

	private String id;
	private String team_id;
	private String name;
	private String username;
	
}
