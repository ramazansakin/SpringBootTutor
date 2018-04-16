package com.sakinramazan.eventpro.controller;

import com.sakinramazan.eventpro.exception.EventNotFoundException;
import com.sakinramazan.eventpro.model.Event;
import com.sakinramazan.eventpro.repository.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

	@Autowired
	EventRepository evetnDbRepository;

	@GetMapping("/events")
	public List<Event> getAllevents() {
		return evetnDbRepository.findAll();
	}

	@PostMapping("/events")
	public Event createEvent(@Valid @RequestBody Event event) {
		return evetnDbRepository.save(event);
	}

	@GetMapping("/events/{id}")
	public Event getEventById(@PathVariable(value = "id") Long eventId) {
		return evetnDbRepository.findById(eventId)
				.orElseThrow(() -> new EventNotFoundException("Event", "id", eventId));
	}

	@PutMapping("/events/{id}")
	public Event updateEvent(@PathVariable(value = "id") Long eventId, @Valid @RequestBody Event eventDetails) {

		Event event = evetnDbRepository.findById(eventId)
				.orElseThrow(() -> new EventNotFoundException("Event", "id", eventId));

		event.setTitle(eventDetails.getTitle());
		event.setContent(eventDetails.getContent());

		Event updatedEvent = evetnDbRepository.save(event);
		return updatedEvent;
	}

	@DeleteMapping("/events/{id}")
	public ResponseEntity<?> deleteEvent(@PathVariable(value = "id") Long eventId) {
		Event event = evetnDbRepository.findById(eventId)
				.orElseThrow(() -> new EventNotFoundException("Event", "id", eventId));

		evetnDbRepository.delete(event);

		return ResponseEntity.ok().build();
	}
}
