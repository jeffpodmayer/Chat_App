package com.coderscampus.miriamassignment14.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import com.coderscampus.miriamassignment14.domain.Channel;
import com.coderscampus.miriamassignment14.domain.Message;
import com.coderscampus.miriamassignment14.domain.User;
import com.coderscampus.miriamassignment14.dto.MessageDTO;
import com.coderscampus.miriamassignment14.service.ChannelService;
import com.coderscampus.miriamassignment14.service.MessageService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MessageController {
	
	private final MessageService messageService;
	private final ChannelService channelService;
	
	@Autowired
	public MessageController(MessageService messageService, ChannelService channelService) {
		this.messageService = messageService;
		this.channelService = channelService;
	}
	
	@PostMapping("/channels/{channelId}/messages")
	public String postMessage(@PathVariable Long channelId, @RequestBody MessageDTO messageDTO, HttpSession session) {
		User user = (User) session.getAttribute("user");
		Channel channel = channelService.findById(channelId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found"));
		Message message = new Message();
		message.setContent(messageDTO.getContent());
		message.setUser(user);
		message.setChannel(channel);
		messageService.save(message);
		
		return "redirect:/channels/{channelId}";
	}

}
