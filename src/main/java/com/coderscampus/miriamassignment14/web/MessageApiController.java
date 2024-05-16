package com.coderscampus.miriamassignment14.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.coderscampus.miriamassignment14.domain.Channel;
import com.coderscampus.miriamassignment14.domain.Message;
import com.coderscampus.miriamassignment14.dto.MessageDTO;
import com.coderscampus.miriamassignment14.service.ChannelService;
import com.coderscampus.miriamassignment14.service.MessageService;

@Controller
@RequestMapping("/api/messages")
public class MessageApiController {

	private final MessageService messageService;
	private final ChannelService channelService;

	@Autowired
	public MessageApiController(MessageService messageService, ChannelService channelService) {
		this.messageService = messageService;
		this.channelService = channelService;
	}

//	@PostMapping("/createMessage/${channelId}")
//	public ResponseEntity<Message> createMessage(@RequestBody MessageDTO messageDTO, HttpSession session) {
//		User user = (User) session.getAttribute("user");
//		if (user == null) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
//		}
//
//		Channel channel = channelService.findById(messageDTO.getChannelId())
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found"));
//
//		Message message = new Message();
//		message.setContent(messageDTO.getContent());
//		message.setChannel(channel);
//		message.setUser(user);
//
//		messageService.save(message);
//
//		return ResponseEntity.ok().body("Message posted successfully");
//	}
	
	@PostMapping("/createMessage/{channelId}")
	public ResponseEntity<Message> createMessage(@PathVariable Long channelId, @RequestBody Message message) {
//		User user = (User) session.getAttribute("user");
//		if (user == null) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
//		}

		Channel channel = channelService.findById(channelId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found"));

		Message newMessage = new Message();
		newMessage.setContent(message.getContent());
		newMessage.setChannel(channel);
		newMessage.setUser(message.getUser());
		System.out.println(newMessage);
		Message savedMessage = messageService.save(newMessage);
		
		return ResponseEntity.ok(savedMessage);
	}

	//added username field to messageDTO and modified below
	@GetMapping("/channels/{channelId}/messages")
	public ResponseEntity<List<Message>> getMessages(@PathVariable Long channelId, @RequestParam(required=false) Long since) {
		List<Message> messages;
		if (since != null) {
			messages = messageService.findNewMessagesByChannelId(channelId, since);
		} else {
			messages = messageService.findMessagesByChannelId(channelId);
		}
		return ResponseEntity.ok(messages);
	}

}
