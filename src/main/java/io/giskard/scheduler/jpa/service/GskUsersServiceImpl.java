package io.giskard.scheduler.jpa.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.giskard.scheduler.jpa.model.GskUsers;
import io.giskard.scheduler.jpa.repository.GskUsersRepository;

@Service
@Transactional

public class GskUsersServiceImpl implements GskUsersService {
	@Autowired
	private GskUsersRepository gskUsersRepository;

	@Transactional
	@Override
	public GskUsers save(GskUsers user) {
		return this.gskUsersRepository.save(user);

	}

	@Override
	public Optional<GskUsers> findById(long id) {
		return gskUsersRepository.findById(id);
	}

	@Override
	public Optional<GskUsers> findGskUsersByEmail(String email) {
		return Optional.of(gskUsersRepository.findByEmail(email));
	}

	@Override
	public List<GskUsers> findAllGskUsers() {
		return gskUsersRepository.findAll();
	}

	@Transactional
	@Override
	public void delete(long id) {
		this.gskUsersRepository.deleteById(id);
	}
	
}
