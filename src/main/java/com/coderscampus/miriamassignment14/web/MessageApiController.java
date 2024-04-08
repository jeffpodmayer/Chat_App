package com.coderscampus.miriamassignment14.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.coderscampus.miriamassignment14.domain.Channel;
import com.coderscampus.miriamassignment14.domain.Message;
import com.coderscampus.miriamassignment14.domain.User;
import com.coderscampus.miriamassignment14.dto.MessageDTO;
import com.coderscampus.miriamassignment14.service.ChannelService;
import com.coderscampus.miriamassignment14.service.MessageService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/messages")
public class MessageApiController {

    private final MessageService messageService;
    private final ChannelService channelService;

    @Autowired
    public MessageApiController(MessageService messageService, ChannelService channelService) {
        this.messageService = messageService;
        this.channelService = channelService;
    }

    @PostMapping("/createMessage")
    public ResponseEntity<?> createMessage(@RequestBody MessageDTO messageDTO, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }

        Channel channel = channelService.findById(messageDTO.getChannelId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Channel not found"));

        Message message = new Message();
        message.setContent(messageDTO.getContent());
        message.setChannel(channel);
        message.setUser(user); 

        messageService.save(message);

        return ResponseEntity.ok().body("Message posted successfully");
    }

}
