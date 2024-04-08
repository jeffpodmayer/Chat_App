package com.coderscampus.miriamassignment14.dto;

public class MessageDTO {
	private String content;
	private Long channelId; 

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}
}
