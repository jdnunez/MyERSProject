package com.revature;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controllers.TicketController;
import com.revature.controllers.UserController;

import io.javalin.Javalin;

public class MainApp {
	
	private static Logger logger = LoggerFactory.getLogger(MainApp.class);
 
	public static void main(String[] args) {
		// Here is the main entrypoint for the app
		// Here we will start the server with Javalin 
		// and then expose our endpoints
		Javalin app = Javalin.create().start(9090);
		
		// Before and after each endpoint call
		app.before(ctx -> {
			logger.info("Request at URL " + ctx.url() + " has started.");
		});
		
		app.after(ctx -> {
			logger.info("Request at URL " + ctx.url() + " is complete.");
		});
		
		// GET - users
		app.get("/test", ctx -> {
			logger.info("Testing app...");
			ctx.html("Welcome to our website");
		});
		
		// GET - tickets
		app.get("/tickets/getMyTickets/{id}", TicketController.getList); // Get all tickets for logged in user
		
		// POST - users
		app.post("/users/register", UserController.register);
		app.post("/users/login", UserController.login);
		
		// POST - tickets
		app.post("/tickets/create", TicketController.createTicket);
		//app.post("/tickets/getWorkerTickets", null)
		
		// PUT - tickets
		app.put("/tickets/setStatus/{id}", TicketController.setStatus); // 
		
	}

}
