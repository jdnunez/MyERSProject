package com.revature.dao;

import com.revature.models.Ticket;
import com.revature.models.User;

public interface TicketDAO {

	// create
	int create(Ticket ticket);
	
	// read
	Ticket getByTicketId(int ticketId);
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
