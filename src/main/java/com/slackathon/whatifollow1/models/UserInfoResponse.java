package com.slackathon.whatifollow1.models;

public class UserInfoResponse {
	
	private boolean ok;
	private User user;
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public boolean isIs_admin() {
		return is_admin;
	}
	public void setIs_admin(boolean is_admin) {
		this.is_admin = is_admin;
	}
	public boolean isIs_owner() {
		return is_owner;
	}
	public void setIs_owner(boolean is_owner) {
		this.is_owner = is_owner;
	}
	public boolean isIs_primary_owner() {
		return is_primary_owner;
	}
	public void setIs_primary_owner(boolean is_primary_owner) {
		this.is_primary_owner = is_primary_owner;
	}
	public boolean isIs_restricted() {
		return is_restricted;
	}
	public void setIs_restricted(boolean is_restricted) {
		this.is_restricted = is_restricted;
	}
	public boolean isIs_ultra_restricted() {
		return is_ultra_restricted;
	}
	public void setIs_ultra_restricted(boolean is_ultra_restricted) {
		this.is_ultra_restricted = is_ultra_restricted;
	}
	public boolean isIs_bot() {
		return is_bot;
	}
	public void setIs_bot(boolean is_bot) {
		this.is_bot = is_bot;
	}
	public boolean isIs_app_user() {
		return is_app_user;
	}
	public void setIs_app_user(boolean is_app_user) {
		this.is_app_user = is_app_user;
	}
	public long getUpdated() {
		return updated;
	}
	public void setUpdated(long updated) {
		this.updated = updated;
	}
	public boolean isHas_2fa() {
		return has_2fa;
	}
	public void setHas_2fa(boolean has_2fa) {
		this.has_2fa = has_2fa;
	}
	private boolean is_admin;
	private boolean is_owner;
	private boolean is_primary_owner;
	private boolean is_restricted;
	private boolean is_ultra_restricted;
	private boolean is_bot;
	private boolean is_app_user;
	private long updated;
	private boolean has_2fa;

}
