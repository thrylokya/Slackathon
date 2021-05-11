package com.slackathon.whatifollow1.models;

public class Authorizations {

	private String enterprise_id;
	private String team_id;
	private String user_id;
	private boolean is_bot;
	private boolean is_enterprise_install;
	public String getEnterprise_id() {
		return enterprise_id;
	}
	public void setEnterprise_id(String enterprise_id) {
		this.enterprise_id = enterprise_id;
	}
	public String getTeam_id() {
		return team_id;
	}
	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public boolean isIs_bot() {
		return is_bot;
	}
	public void setIs_bot(boolean is_bot) {
		this.is_bot = is_bot;
	}
	public boolean isIs_enterprise_install() {
		return is_enterprise_install;
	}
	public void setIs_enterprise_install(boolean is_enterprise_install) {
		this.is_enterprise_install = is_enterprise_install;
	}
}
