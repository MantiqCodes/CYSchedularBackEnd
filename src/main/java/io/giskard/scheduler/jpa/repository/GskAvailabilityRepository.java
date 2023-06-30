package io.giskard.scheduler.jpa.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.Predicate;

import io.giskard.scheduler.jpa.model.GskAvailability;

@Repository

public interface GskAvailabilityRepository
		extends JpaRepository<GskAvailability, Long>, QuerydslPredicateExecutor<GskAvailability> {

//	@Query(value="SELECT * FROM  GSK_AVAILABLITLIY WHERE START_TIME >= :fromSTime AND START_TIME <= :toSTime", nativeQuery=true)
	@Query(value="SELECT av FROM  GskAvailability av WHERE av.startTime >= :fromSTime AND av.startTime <= :toSTime AND av.gskUsers.email=:email")
	List<GskAvailability> findAllByStartTimeBetween(@Param("fromSTime") Date fromSTime, @Param("toSTime") Date toSTime,@Param("email") String email);

}
