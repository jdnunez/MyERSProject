package com.revature.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.models.Ticket;
import com.revature.models.User;
import com.revature.services.ImpTicketServices;
import com.revature.services.TicketServices;

import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;

public class TicketController {

	private static Logger logger = LoggerFactory.getLogger(TicketController.class);
	
	private static TicketServices tkServ = new ImpTicketServices();
	
public static Handler createTicket = ctx -> {
		
		logger.info("Creating new ticket...");
		
		String body = ctx.body();
		
		ObjectMapper om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		Ticket tkTarget = om.readValue(body, Ticket.class);
		
		boolean isCreated = tkServ.createTicket(tkTarget);
		
		if (isCreated) {
			ctx.html("New ticket created!");
			ctx.status(HttpStatus.CREATED);
		} else {
			ctx.html("Could not create ticket! Try again.");
			ctx.status(HttpStatus.NO_CONTENT);
		}
	};
}
