package com.coderscampus.miriamassignment14.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.coderscampus.miriamassignment14.domain.Channel;
import com.coderscampus.miriamassignment14.repository.ChannelRepository;

@Service
public class ChannelService {
	
	private final ChannelRepository channelRepository;
	
	@Autowired
	public ChannelService(ChannelRepository channelRepository) {
		this.channelRepository = channelRepository;
	}
	
	@Transactional(readOnly = true)
	public Optional<Channel> findById(Long id) {
		return channelRepository.findById(id);
	}
	
	@Transactional(readOnly = true)
	public List<Channel> findAll() {
		return channelRepository.findAll();
	}
	
	@Transactional
	public Channel save(Channel channel) {
		return channelRepository.save(channel);
	}
	
	@Transactional
	public void deleteById(Long id) {
		channelRepository.deleteById(id);
	}
	
	
	
}
