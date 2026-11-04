package com.example.Movieticket.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

//Java collection classes used to store and return tickets.
import org.springframework.stereotype.Service;

import com.example.Movieticket.exception.ResourceNotFoundException;
import com.example.Movieticket.model.Ticket;

//Spring creates one object of this class a Bean and keeps it ready.
@Service
public class TicketService {

// Stores tickets as key value pairs
	private final Map<Integer, Ticket> tickets = new LinkedHashMap<>();
	private int nextId = 1;


// POST Book ticket
	public Ticket bookTicket(Ticket ticket) {
// post increment it uses the current value first, then adds 1.
		ticket.setTicketId(nextId++);
// Saves the ticket in the map with its ID as the key
		tickets.put(ticket.getTicketId(), ticket);
		return ticket;
	}

	
// get All Tickets
	public List<Ticket> getAllTickets() {
// gets all the Ticket objects in the map
		return new ArrayList<>(tickets.values());
	}

	
// GET by ID -> looks up the ticket by key. If the key doesn’t exist, it returns null.
	public Ticket getTicketById(int id) {
		Ticket ticket = tickets.get(id);
		if (ticket == null) {
			throw new ResourceNotFoundException("Ticket not found with id: " + id);
		}
		return ticket;
	}

	
// update
	public Ticket updateTicket(int id, Ticket newTicket) {
//checks the ticket exists.
		getTicketById(id);
// new data from the body has no ID or might have a wrong one, so we set the ID from the URL.
		newTicket.setTicketId(id);
// when you put with a key that already exists, the old value is replaced. So the whole old ticket is swapped for the new one.
		tickets.put(id, newTicket);
		return newTicket;
	}

	
// Partial update
// holds only the fields the user sent.
	public Ticket patchTicket(int id, Map<String, Object> updates) {
		Ticket existing = getTicketById(id);

// entry set give every key value pair in the map.
		for (Map.Entry<String, Object> entry : updates.entrySet()) {
// the loop handle one field at a time
			String field = entry.getKey();
			Object value = entry.getValue();


// compares the filed name 
			if (field.equals("movieName")) {
// the user sent null or removes spaces at the start and end and check if nothing is left 
				if (value == null || value.toString().trim().isEmpty()) {
					throw new IllegalArgumentException("Movie name is required");
				}
				existing.setMovieName(value.toString());

// Same check as movieName, with its own message.
			} else if (field.equals("customerName")) {
				if (value == null || value.toString().trim().isEmpty()) {
					throw new IllegalArgumentException("Customer name cannot be empty");
				}
				existing.setCustomerName(value.toString());

// seatNumber has no validation, so it’s updated directly.
			} else if (field.equals("seatNumber")) {
				existing.setSeatNumber(value == null ? null : value.toString());

// value instance of Number checks the value is actually a number.
			} else if (field.equals("ticketPrice")) {
				if (!(value instanceof Number) || ((Number) value).doubleValue() <= 0) {
					throw new IllegalArgumentException("Ticket price must be positive");
				}
				existing.setTicketPrice(((Number) value).doubleValue());

			} else {
				throw new IllegalArgumentException("Invalid field: " + field);
			}
		}
		return existing;
	}

// DELETE ticket
	public void deleteTicket(int id) {
		getTicketById(id);
		tickets.remove(id);
	}
}