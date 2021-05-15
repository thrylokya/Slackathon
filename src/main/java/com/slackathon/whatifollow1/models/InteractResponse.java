package com.slackathon.whatifollow1.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteractResponse {
	
	private String type;
	private User user;
	private List<Actions> actions;
	
}
