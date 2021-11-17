package com.nag.spring.datajpa.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nag.spring.datajpa.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, String> {
	List<Participant> findByDateOfJoining(Date dateOfJoining);
	List<Participant> findByFirstNameContaining(String firstName);
	Optional<Participant> findByEmailId(String emailId);
}
