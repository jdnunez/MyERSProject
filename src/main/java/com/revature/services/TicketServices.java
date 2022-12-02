package com.revature.services;

import java.util.List;

import com.revature.models.Ticket;
import com.revature.models.User;

public interface TicketServices {
	
	// Submit ticket
	public boolean createTicket(Ticket ticket);
	
	// Managers can approve or deny
	public boolean changeStatus(Ticket ticket); // Easiest (just edit body in Postman)
	public boolean changeStatus(int ticketID, String status); // Better? Difficult?
	
	// Users can view previous submissions and filter based on status
	public List<Ticket> getUserTickets(int userId);
	public List<Ticket> getUserTickets(int userId, String tkStatus);
	public List<Ticket> getUserTickets(String username);
	public List<Ticket> getUserTickets(String username, String tkStatus);
	
}
