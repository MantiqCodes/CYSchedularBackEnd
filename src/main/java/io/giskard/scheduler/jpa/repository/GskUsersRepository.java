package io.giskard.scheduler.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.giskard.scheduler.jpa.model.GskUsers;

@Repository
public interface GskUsersRepository extends JpaRepository<GskUsers, Long>
{
	GskUsers findByEmail(String email);

}
