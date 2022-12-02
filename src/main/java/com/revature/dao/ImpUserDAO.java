package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.User;
import com.revature.util.JDBCConnectionUtil;

public class ImpUserDAO implements UserDAO {

	Connection conn;
	
	private static Logger logger = LoggerFactory.getLogger(ImpUserDAO.class);
	
	public ImpUserDAO() {
		conn = JDBCConnectionUtil.getConnection();
	}
	
	@Override
	public int create(User user) {
		
		String uName = user.getUsername();
		logger.info("ImpUserDAO::create() called. Using username " + uName + "...");
		
//		if (this.getByUsername(uName) == null) {
			try {
				logger.info("ImpUserDAO::create() called. Inserting new user record...");
				
				String sql = "INSERT INTO users (username, password, role) VALUES(?, ?, ?)";
				
				PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				pstmt.setString(1, user.getUsername());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getRole());
				
				pstmt.executeUpdate();
				
				ResultSet rs = pstmt.getGeneratedKeys();
				
				rs.next();
				
				logger.info("ImpUserDAO::create() - new user id is " + rs.getInt("user_id"));
				
				return rs.getInt("user_id");
				
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
//		} else {
//			logger.error("Username is already taken. Try again.");
//		}
		
		return 0;
	}

	@Override
	public User getByUserId(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByUsername(String username) {
		logger.info("ImpUserDAO::getByUserName called. Using username " + username + "...");
		
		try {
			String sql = "SELECT * FROM users WHERE username=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, username);
			
			// by using executeQuery() we get a result set back
			ResultSet rs = pstmt.executeQuery();
			
			//we are creating an instance of our User b/c we will
			// have to return an User
			
			User user = new User();
			logger.info("ImpUserDAO::getByUserName - Inside try block...");
			
			// this resultset that we get back from our query is what were
			//iterating through in order to make out our User object
			while(rs.next()) {
				user.setUserId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));  
			}
			
			//now that our object's fields have been set, it can be returned that found user 
			return user;
			
		} catch (SQLException sqlEx) {
			logger.error("UserDAOImpl::getByUsername() exception - Message: " + sqlEx.getMessage());
		}
		
		//else if no user is found, return null
		return null;
	}

	@Override
	public boolean update(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteByUserId(int userId) {
		// TODO Auto-generated method stub
		return false;
	}

}
