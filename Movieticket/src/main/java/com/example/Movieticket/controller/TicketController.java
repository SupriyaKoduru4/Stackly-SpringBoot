package com.example.Movieticket.controller;

//used to return many tickets
import java.util.List;
//used to receive only some fields in PATCH.
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Movieticket.model.Ticket;
import com.example.Movieticket.service.TicketService;

import jakarta.validation.Valid;

//Spring this class handles REST API requests.
@RestController
@RequestMapping("/tickets")
public class TicketController {

// controller doesn’t do the work itself
	private final TicketService service;

	public TicketController(TicketService service) {
		this.service = service;
	}


// POST method Book ticket
	@PostMapping
	public ResponseEntity<Ticket> bookTicket(@Valid @RequestBody Ticket ticket) {
		Ticket saved = service.bookTicket(ticket);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	
// GET  all tickets
	@GetMapping
	public List<Ticket> getAllTickets() {
		return service.getAllTickets();
	}

	
// GET By ID
	@GetMapping("/{id}")
	public Ticket getTicketById(@PathVariable("id") int id) {
		return service.getTicketById(id);
	}

	
// update
	@PutMapping("/{id}")
	public Ticket updateTicket(@PathVariable("id") int id, @Valid @RequestBody Ticket ticket) {
		return service.updateTicket(id, ticket);
	}


// Partial update
	@PatchMapping("/{id}")
	public Ticket patchTicket(@PathVariable("id") int id, @RequestBody Map<String, Object> updates) {
		return service.patchTicket(id, updates);
	}

	
// DELETE ticket
	@DeleteMapping("/{id}")
	public String deleteTicket(@PathVariable("id") int id) {
		service.deleteTicket(id);
		return "Ticket cancelled successfully with id: " + id;
	}
}