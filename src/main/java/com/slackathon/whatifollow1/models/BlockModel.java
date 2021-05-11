package com.slackathon.whatifollow1.models;

import java.util.List;

public class BlockModel {

	private String type;
	private Text text;
	private String block_id;
	private List<Elements> elements;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	public String getBlock_id() {
		return block_id;
	}
	public void setBlock_id(String block_id) {
		this.block_id = block_id;
	}
	public List<Elements> getElements() {
		return elements;
	}
	public void setElements(List<Elements> elements) {
		this.elements = elements;
	}
}
