package io.giskard.scheduler.jpa.service;

import java.util.List;
import java.util.Optional;

import io.giskard.scheduler.jpa.model.GskUsers;

public interface GskUsersService
{

	public Optional<GskUsers> findById(long id);
	public Optional<GskUsers> findGskUsersByEmail(String email);
	public List<GskUsers> findAllGskUsers();
	public GskUsers save(GskUsers user);
	public void delete(long id);
}
