package com.coderscampus.miriamassignment14.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.coderscampus.miriamassignment14.domain.Channel;
import com.coderscampus.miriamassignment14.domain.Message;
import com.coderscampus.miriamassignment14.domain.User;
import com.coderscampus.miriamassignment14.service.ChannelService;
import com.coderscampus.miriamassignment14.service.MessageService;
import com.coderscampus.miriamassignment14.service.UserService;

@Controller
public class ChannelController {

	private final ChannelService channelService;
	private final MessageService messageService;
	private final UserService userService;

	@Autowired
	public ChannelController(ChannelService channelService, MessageService messageService, UserService userService) {
		this.channelService = channelService;
		this.messageService = messageService;
		this.userService = userService;
	}

	@GetMapping("/channels/{userId}/{channelId}")
	public String viewChannel(@PathVariable Long channelId, @PathVariable Long userId, Model model) {
		Channel channel = channelService.findById(channelId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found"));
//		System.out.println("Channel Name: " + channel.getName()); // For debugging
		List<Message> messages = messageService.findMessagesByChannelId(channelId);
		Optional<User> optionalUser = userService.findById(userId);
		User user = optionalUser.get();
		model.addAttribute("user", user);
		model.addAttribute("channel", channel);
		model.addAttribute("messages", messages);

		return "channel";
	}

	@GetMapping("/channels/{userId}")
	public String showChannels(@PathVariable Long userId, Model model) {
		List<Channel> channels = channelService.findAll();
		Optional<User> optionalUser = userService.findById(userId);
		User user = optionalUser.get();
		Channel channel = new Channel();
		model.addAttribute("user", user);
		model.addAttribute("channels", channels);
		model.addAttribute("channel", channel);
		return "channels";
	}

	@PostMapping("/channels/createChannel/{userId}")
		public String createChannel(String name, @PathVariable Long userId) {
		Channel newChannel = new Channel();
		newChannel.setName(name);
		Channel savedChannel = channelService.save(newChannel);
		System.out.println(name);
		System.out.println(savedChannel.getId());
//		redirectAttributes.addFlashAttribute("successMessage", "Channel '" + channelName + "' created successfully");
		
		return "redirect:/channels/" + userId;
	}

}
