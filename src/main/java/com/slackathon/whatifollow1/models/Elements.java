package com.slackathon.whatifollow1.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Elements {
	
	private String type;
	private String user_id;
	private String name;
	private List<Elements> elements;
	private String text;
	private String channelId;
	private String url;
	private String teamId;
	private String usergroupId;
	private String timestamp;
	private String range;
	private String value;
}
