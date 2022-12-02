package com.revature.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.revature.dao.ImpUserDAO;
import com.revature.dao.UserDAO;
import com.revature.models.User;
import com.revature.services.ImpUserServices;
import com.revature.services.UserServices;

class UserServicesTest {
	
	private static UserDAO userDAO;
	private static UserServices uServ = new ImpUserServices();
	private static User temp;
	private static int userId;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		userDAO = Mockito.mock(ImpUserDAO.class);
		temp = new User("joanun", "password123", "employee");
		
	}

	@Test
	@DisplayName("1. Register user - Normal Test")
	void testRegisterUser() {
		// 1. Arrange - Here we need to tell Mockito what to do if given a specific situation in the program
		when(userDAO.create(temp)).thenReturn(userId);
		
		// 2. Act - Do the service call
		boolean result = uServ.registerUser(temp);
		
		// 3. Assert - Checking if the registerUser returns a boolean object that matches temp user
		assertEquals(temp, result);
		
		// 4. Verify - Verifying that the DAO method was used once in the execution of the service call
		verify(userDAO, times(1)).getById(1);
	}

}
