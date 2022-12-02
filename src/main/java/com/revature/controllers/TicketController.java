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
	
	public static Handler setStatus = ctx -> {
		// 1. Get ticket info from request body
		int id = Integer.parseInt(ctx.pathParam("id"));
		String body = ctx.body();
		
		// Convert the body into a Ticket object
		ObjectMapper om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		Ticket target = om.readValue(body, Ticket.class);
		target.setTicketId(id);
		
		// 2. Do a service call
		boolean isUpdated = tkServ.setStatus(target);
				
	};
	
	/*
	public static Handler update = ctx -> {
		//1. get user info from request body
		int id = Integer.parseInt(ctx.pathParam("id"));
		String body = ctx.body();
		
		//here we will need to convert the body into a User object
		ObjectMapper om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		User target = om.readValue(body, User.class);
		target.setId(id);
		
		//2. do service call
		boolean isUpdated = uServ.updateUser(target);
		
		//3. render response
		if(isUpdated == true) {
			ctx.html("User ID# "+ id +" : Your user information has been updated successfully.");
			ctx.status(HttpStatus.OK);
		}else {
			ctx.html("Error during update. Try again.");
			ctx.status(HttpStatus.BAD_REQUEST);
		}
		
	};
	*/
}
