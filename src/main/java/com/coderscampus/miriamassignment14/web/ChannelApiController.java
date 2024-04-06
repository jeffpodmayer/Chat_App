package com.coderscampus.miriamassignment14.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.miriamassignment14.domain.Channel;
import com.coderscampus.miriamassignment14.dto.ChannelDTO;
import com.coderscampus.miriamassignment14.service.ChannelService;

@RestController
@RequestMapping("/api/channels")
public class ChannelApiController {
	
	private final ChannelService channelService;
	
	@Autowired
	public ChannelApiController(ChannelService channelService) {
		this.channelService = channelService;
	}
	
	@PostMapping("/createChannel")
	public ResponseEntity<?> createChannel(@RequestBody ChannelDTO channelDto) {
		Channel newChannel = new Channel();
		newChannel.setName(channelDto.getChannelName());
		Channel savedChannel = channelService.save(newChannel);
		
		Map<String, Object> response = new HashMap<>();
		response.put("id", savedChannel.getId());
		response.put("name", savedChannel.getName());
		return ResponseEntity.ok(response);
	}

}

