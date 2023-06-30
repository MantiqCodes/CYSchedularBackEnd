package io.giskard.scheduler.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import io.giskard.scheduler.jpa.model.GskReservation;

@Repository
public interface GskReservationRepository extends JpaRepository<GskReservation,Long>,QuerydslPredicateExecutor<GskReservation>
{
	@Query(value="SELECT rv FROM  GskReservation rv WHERE rv.startTime >= :fromSTime AND rv.startTime <= :toSTime AND rv.gskUsers.email=:email")
	List<GskReservation> findAllByStartTimeBetween(@Param("fromSTime") Date fromSTime, @Param("toSTime") Date toSTime,@Param("email") String email);

}
