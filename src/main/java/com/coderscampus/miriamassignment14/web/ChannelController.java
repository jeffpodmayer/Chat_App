package com.coderscampus.miriamassignment14.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.coderscampus.miriamassignment14.service.ChannelService;

@Controller
public class ChannelController {
	
	private final ChannelService channelService;
	
	@Autowired
	public ChannelController(ChannelService channelService) {
		this.channelService = channelService;
	}
	
	@GetMapping("/channels/{channelId}")
	public String viewChannel(@PathVariable Long channelId, Model model) {
		
		return "channel";
	}
	
	

}
