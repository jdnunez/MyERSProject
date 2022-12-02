package com.revature.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.revature.MainApp;
import com.revature.models.LoginTemplate;
import com.revature.models.User;
import com.revature.services.ImpUserServices;
import com.revature.services.UserServices;

import io.javalin.http.Handler;
import io.javalin.http.HttpStatus;
import jakarta.servlet.http.Cookie;

public class UserController {
	public static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private static UserServices uServ = new ImpUserServices();
	
	public static Handler register = ctx -> {
		
		logger.info("Registering new user...");
		
		String body = ctx.body();
		
		ObjectMapper om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		User target = om.readValue(body, User.class);
		
		boolean isCreated = uServ.registerUser(target);
		
		if (isCreated) {
			ctx.html("New user registered!");
			ctx.status(HttpStatus.CREATED);
		} else {
			ctx.html("Could not register user! Try again.");
			ctx.status(HttpStatus.NO_CONTENT);
		}
	};
	
	public static Handler login = ctx -> {
		//1. get user info from request body
		String body = ctx.body();
		
		//here we will need to convert the body into a User object
		ObjectMapper om = new ObjectMapper();
		LoginTemplate target = om.readValue(body, LoginTemplate.class);
		
		//2. do service call
		boolean isAuthenticated = uServ.login(target.getUsername(), target.getPassword());
		
		//3. render response
		// Checking to see if user exists in the system
		if (isAuthenticated == true) {
			// if true - creating a HTTP cookie for our logged in user and sending that cookie in the HTTP response
			ctx.html("Successful login. Welcome " + target.getUsername() + "!");
			
			//authorize user
			// using Javalin's CookieSet to save our session cookie for later authorization for admin-level methods (e.g.
			// only allow deletions if user is manager
			ctx.cookieStore().set("Auth-Cookie", target.getUsername() + "-8675309"); // Unique cookie-name and username plus additional identifying suffix
			Cookie auth = new Cookie("Auth-Cookie", target.getUsername() + "98210");
			ctx.res().addCookie(auth); // Adds the HTTP cookie to the response header
			ctx.status(HttpStatus.OK);
		} else {
			ctx.html("Invalid username and/or password. Please try again.");
			ctx.status(HttpStatus.UNAUTHORIZED);
		}
	};
	
	public static Handler deleteUser = ctx -> {
		
		//1. get user id from path
		int id = Integer.parseInt(ctx.pathParam("id"));
		logger.info("Deleting user id# " + id + "...");
		//2. do service call
		// a. Get our HTTP cookie from our request header
		String ck = ctx.req().getHeader("Auth-Cookie").replaceAll("98210", "");
		logger.info("Authentication cookie: " + ck);
		
		// b. get the logged in user info
		User target = uServ.getUserByUsername(ck);
		logger.info("Based on cookie, this is your logged in user: " + target);
		
		//3. render response
		// If current user matches given admin username, then do the deletion
		if (target.getUsername().equalsIgnoreCase("jdnunez")) {
			boolean isDeleted = uServ.deleteUser(id);
			
			if(isDeleted == true) {
				ctx.html("User ID# "+ id +" has been removed from the system successfully.");
				ctx.status(HttpStatus.OK);
			} else {
				ctx.html("Error during deletion. Try again.");
				ctx.status(HttpStatus.BAD_REQUEST);
			}
		} else {
			// If not me, then send a NotAuthorized status code back in Http response
			ctx.html("Sorry, this user is not authorized to perform this operation.");
			ctx.status(HttpStatus.UNAUTHORIZED);
		}
		
		
	};
	
	
}
