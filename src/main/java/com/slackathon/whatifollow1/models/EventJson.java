package com.slackathon.whatifollow1.models;

import java.util.List;

public class EventJson {
	
	private String token;
	private String team_id;
	private String api_app_id;
	private Event event;
	private String type;
	private String challenge;
	private String event_id;
	private String event_time;
	private boolean is_ext_shared_channel;
	private String event_context;
	private List<Authorizations> authorizations;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}
	public String getApi_app_id() {
		return api_app_id;
	}
	public void setApi_app_id(String api_app_id) {
		this.api_app_id = api_app_id;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getChallenge() {
		return challenge;
	}
	public void setChallenge(String challenge) {
		this.challenge = challenge;
	}
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getEvent_time() {
		return event_time;
	}
	public void setEvent_time(String event_time) {
		this.event_time = event_time;
	}
	public boolean isIs_ext_shared_channel() {
		return is_ext_shared_channel;
	}
	public void setIs_ext_shared_channel(boolean is_ext_shared_channel) {
		this.is_ext_shared_channel = is_ext_shared_channel;
	}
	public String getEvent_context() {
		return event_context;
	}
	public void setEvent_context(String event_context) {
		this.event_context = event_context;
	}
	public List<Authorizations> getAuthorizations() {
		return authorizations;
	}
	public void setAuthorizations(List<Authorizations> authorizations) {
		this.authorizations = authorizations;
	}
	
	
	
	

}
