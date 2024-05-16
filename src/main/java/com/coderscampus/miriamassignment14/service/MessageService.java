package com.coderscampus.miriamassignment14.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderscampus.miriamassignment14.domain.Message;
import com.coderscampus.miriamassignment14.repository.MessageRepository;

@Service
public class MessageService {

	private final MessageRepository messageRepository;

	@Autowired
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;

	}
	
	@Transactional(readOnly = true)
	public Optional<Message> findById(Long id) {
		return messageRepository.findById(id);
		
	}
	
	@Transactional(readOnly = true)
	public List<Message> findAll() {
		return messageRepository.findAll();
		
	}
	
	@Transactional
	public Message save(Message message) {
		return messageRepository.save(message);
		
	}
	
	@Transactional
	public void deleteById(Long id) {
		messageRepository.deleteById(id);
	}

	@Transactional(readOnly = true)
	public List<Message> findMessagesByChannelId(Long channelId) {
		return messageRepository.findByChannelId(channelId);
	}

    public List<Message> findNewMessagesByChannelId(Long channelId, Long since) {
		return messageRepository.findByChannelIdAndIdGreaterThan(channelId, since);
    }
}
