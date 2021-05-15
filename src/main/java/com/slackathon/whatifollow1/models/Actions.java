package com.slackathon.whatifollow1.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Actions {

	private String type;
	private String action_id;
	private String block_id;
	private String selected_user;
	private String action_ts;
	private Text text;
	private String value;
}
