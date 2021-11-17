package com.nag.spring.datajpa.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nag.spring.datajpa.model.Participant;
import com.nag.spring.datajpa.repository.ParticipantRepository;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class ParticipantController {

	@Autowired
	ParticipantRepository participantRepository;

	@GetMapping("/participant/enroll")
	public ResponseEntity<List<Participant>> getAllParticipants(@RequestParam(required = false) String title) {
		try {
			List<Participant> participants = new ArrayList<Participant>();

			if (title == null)
				participantRepository.findAll().forEach(participants::add);
			else
				participantRepository.findByFirstNameContaining(title).forEach(participants::add);

			if (participants.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(participants, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/participant/getByEmailId")
	public ResponseEntity<Participant> getPartcipantById(@RequestParam("emailId") String emailId) {
		Optional<Participant> tutorialData = participantRepository.findByEmailId(emailId);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/participant/enroll")
	public ResponseEntity<Participant> createParticipantEnroll(@RequestBody Participant tutorial) {
		try {
			Participant participant = new Participant(tutorial.getFirstName(), tutorial.getLastName(),tutorial.getEmailId(),tutorial.getDateOfBirth());
			Participant _tutorial = participantRepository
					.save(participant);
			return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * @PutMapping("/partcipant/{id}/update") public ResponseEntity<Participant>
	 * updateParticipant(@PathVariable("id") long id, @RequestBody Participant
	 * participantEnroll) { Optional<Participant> retrievedParticipantData =
	 * participantRepository.findById(id);
	 * 
	 * if (retrievedParticipantData.isPresent()) { Participant _enrollupdate =
	 * retrievedParticipantData.get();
	 * _enrollupdate.setFirstName(participantEnroll.getFirstName());
	 * _enrollupdate.setLastName(participantEnroll.getLastName());
	 * _enrollupdate.setEmailId(participantEnroll.getEmailId());
	 * _enrollupdate.setDateOfBirth(participantEnroll.getDateOfBirth());
	 * _enrollupdate.setActivationStatus(participantEnroll.isActivationStatus());
	 * return new ResponseEntity<>(participantRepository.save(_enrollupdate),
	 * HttpStatus.OK); } else { return new ResponseEntity<>(HttpStatus.NOT_FOUND); }
	 * }
	 */
	
	
	/*
	 * @DeleteMapping("/partcipant/{id}") public ResponseEntity<HttpStatus>
	 * deleteParticipant(@PathVariable("id") long id) { try {
	 * participantRepository.deleteById(id); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */

	@DeleteMapping("/participants")
	public ResponseEntity<HttpStatus> deleteAllParticipants() {
		try {
			participantRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/participants/dateOfJoining")
	public ResponseEntity<List<Participant>> findAllByDateOfJoining() {
		try {
			List<Participant> tutorials = participantRepository.findByDateOfJoining(new Date(new java.util.Date().getTime()));

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
