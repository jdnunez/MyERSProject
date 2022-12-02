package com.revature.controllers;

import java.util.List;

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
	
	// From /tickets/getMyTickets
	public static Handler getList = ctx -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		logger.info("Obtaining ticket list from user id# " + id + "...");
		
		List<Ticket> tkList = tkServ.getUserTickets(id);
		System.out.println(tkList);
		if (tkList != null) {
			ctx.json(tkList);
//			for (Ticket next : tkList) {
//				ctx.json(next);
//				System.out.println(next);
//			}
		} else {
			ctx.html("Error during ticket list search by that id. Try again.");
			ctx.status(HttpStatus.NOT_FOUND);
		}
	};
	
	/*
	public static Handler getUserById = ctx -> {
		//to retrieve info from the url, we can use our ContextHandler from Javalin
		int id = Integer.parseInt(ctx.pathParam("id"));
		
		User target = uServ.getUserById(id);
		
		if(target != null && target.getUsername() != null) {
			ctx.json(target);
		}else {
			ctx.html("Error during user search by that id. Try again.");
			ctx.status(HttpStatus.NOT_FOUND);
		}
	};
	*/
}
