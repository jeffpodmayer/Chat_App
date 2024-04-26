package com.coderscampus.miriamassignment14.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

	@PostMapping("/createMessage/{channelId}")
	public ResponseEntity<Message> createMessage(@PathVariable Long channelId, @RequestBody Message message) {
		Channel channel = channelService.findById(channelId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found"));

		Message newMessage = new Message();
		newMessage.setContent(message.getContent());
		newMessage.setChannel(channel);
		newMessage.setUser(message.getUser());

		Message savedMessage = messageService.save(newMessage);

		return ResponseEntity.ok(savedMessage);
	}
	
//	@PostMapping("/createMessage/{channelId}")
//	public ResponseEntity<Message> createMessage(@RequestBody Message message, @PathVariable Long channelId) {
////		User user = (User) session.getAttribute("user");
////		if (user == null) {
////			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
////		}
//
//		Channel channel = channelService.findById(channelId)
//				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found"));
//
//		Message newMessage = new Message();
//		newMessage.setContent(message.getContent());
//		newMessage.setChannel(channel);
////		newMessage.setUser(channel.getUsers());
//		System.out.println(message);
//		messageService.save(message);
//
//		return ResponseEntity.status(HttpStatus.CREATED).body(message);
//	}

	@GetMapping("/channels/{channelId}/messages")
	public ResponseEntity<List<MessageDTO>> getMessages(@PathVariable Long channelId) {
		List<Message> messages = messageService.findMessagesByChannelId(channelId);
		List<MessageDTO> messageDTOs = messages.stream().map(message -> {
			MessageDTO dto = new MessageDTO();
			dto.setContent(message.getContent());
			dto.setChannelId(message.getChannel().getId());
			return dto;
		}).collect(Collectors.toList());
		return ResponseEntity.ok(messageDTOs);
	}

}
