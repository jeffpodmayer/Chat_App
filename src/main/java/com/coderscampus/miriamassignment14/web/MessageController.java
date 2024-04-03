package com.coderscampus.miriamassignment14.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.coderscampus.miriamassignment14.service.MessageService;

@Controller
public class MessageController {
	
	private final MessageService messageService;
	
	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@PostMapping("/channels/{channelId}/messages")
	public String postMessage() {
		return "redirect:/channels/{channelId}";
	}

}
