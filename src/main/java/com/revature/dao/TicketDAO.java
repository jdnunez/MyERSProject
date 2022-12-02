package com.revature.dao;

import java.util.List;

import com.revature.models.Ticket;

public interface TicketDAO {

	// create
	int create(Ticket ticket);
	
	// read
	Ticket getByTicketId(int ticketId);
	List<Ticket> getByUserId(int userId);
	/*
	 * *** QUESTION ***
	 * Do we read an array list for getting all tickets for a user? Or implement that in services?
	 */
	
	// update
	boolean update(Ticket ticket);
	/*
	 * Managers have permission to update.
	 */
	
	// delete
	boolean deleteByTicketId(int ticketId);
	/*
	 * Managers have permission to delete.
	 */
}
