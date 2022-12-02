package com.revature.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dao.ImpTicketDAO;
import com.revature.dao.TicketDAO;
import com.revature.models.Ticket;
import com.revature.models.User;

public class ImpTicketServices implements TicketServices {

	private static Logger logger =LoggerFactory.getLogger(ImpTicketServices.class);
	
	private static TicketDAO ticketDAO;
	
	public ImpTicketServices() {
		ticketDAO = new ImpTicketDAO();
	}
	
	public ImpTicketServices(ImpTicketDAO td) {
		this.ticketDAO = td;
	}
	
	@Override
	public boolean createTicket(Ticket ticket) {
		// 1. Log event
		logger.info("ImpTicketServices::createTicket");
		// 2. Get ticket id (dao method call)
		int id = ticketDAO.create(ticket);
		// 3. Return true if id exists else return false
		logger.info("Received from DAO. New Ticket ID: " + id);
		
		return (id != 0) ? true : false;
	}

	@Override
	public boolean setStatus(Ticket ticket) {
		logger.info("ImpTicketService::setStatus() called. Updating ticket ID# "+ ticket.getTicketId() +"...");
		return ticketDAO.update(ticket);
	}
	/*
	@Override
	public boolean updateUser(User user) {
		logger.info("UserService::updateUser() called. Updating user ID# "+ user.getId() +"...");
		return userDAO.update(user);
	}
	*/
	
	@Override
	public boolean setStatus(int tkId) {
		// TODO Auto-generated method stub
		return false;
	}
	
	

	@Override
	public boolean setStatus(int ticketID, String status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Ticket> getUserTickets(int userId) {
		logger.info("ImpTicketServices::getUserTickets() called. Trying to obtain ticket list from user id# " + userId + "...");
		return ticketDAO.getByUserId(userId);
	}

	@Override
	public List<Ticket> getUserTickets(int userId, String tkStatus) {
		// TODO Auto-generated method stub
		return ticketDAO.getByUserId(userId, tkStatus);
	}

	@Override
	public List<Ticket> getUserTickets(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> getUserTickets(String username, String tkStatus) {
		// TODO Auto-generated method stub
		return null;
	}

}
