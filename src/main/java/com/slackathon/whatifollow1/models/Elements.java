package com.slackathon.whatifollow1.models;

import java.util.List;

public class Elements {
	
	private String type;
	private String user_id;
	private String name;
	private List<Elements> elements;
	private String text;
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setElements(List<Elements> elements)
	{
		this.elements = elements;
	}
	
	public List<Elements> getElements(){
		return this.elements;
	}
}
