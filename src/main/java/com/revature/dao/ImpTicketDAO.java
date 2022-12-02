package com.revature.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Ticket;
import com.revature.models.User;
import com.revature.util.JDBCConnectionUtil;

public class ImpTicketDAO implements TicketDAO {
	
	Connection conn;
	
	private static Logger logger = LoggerFactory.getLogger(ImpTicketDAO.class);
	
	public ImpTicketDAO() {
		conn = JDBCConnectionUtil.getConnection();
	}
	
	@Override
	public int create(Ticket ticket) {
		try {
			// 1. Prepare our SQL statement
			String sql = "INSERT INTO tickets (user_id, amount, description, status) VALUES (?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			pstmt.setInt(1, ticket.getUserId());
			pstmt.setInt(2, ticket.getAmount());
			pstmt.setString(3, ticket.getDescription());
			pstmt.setString(4, ticket.getStatus());
			
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			
			rs.next();
			
			logger.info("ImpTicketDAO::create() - new ticktet id is " + rs.getInt(1));
			return rs.getInt(1);
			
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
		// Else if no ticket is created, return 0
		return 0;
	}

	@Override
	public Ticket getByTicketId(int ticketId) {
		try {
			String sql = "SELECT * FROM tickets WHERE ticket_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, ticketId);
			
			ResultSet rs = pstmt.executeQuery();
			
			Ticket target = new Ticket();
			
			while(rs.next()) {
				target.setTicketId(rs.getInt("ticket_id"));
				target.setUserId(rs.getInt("user_id"));
				target.setAmount(rs.getInt("amount"));
				target.setDescription(rs.getString("description"));
				target.setStatus(rs.getString("status"));
			}
			
			return target;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<Ticket> getByUserId(int userId) {
		try {
			String sql = "SELECT * FROM tickets WHERE user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userId);
			
			ResultSet rs = pstmt.executeQuery();
			
			List<Ticket> tkList = new ArrayList<Ticket>();
			
			Ticket target = new Ticket();
			
			while(rs.next()) {
				target.setTicketId(rs.getInt("ticket_id"));
				target.setUserId(rs.getInt("user_id"));
				target.setAmount(rs.getInt("amount"));
				target.setDescription(rs.getString("description"));
				target.setStatus(rs.getString("status"));
				tkList.add(target);
				target = new Ticket();
				logger.info("Target added.");
			}
			
			return tkList;
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
	
	@Override
	public List<Ticket> getByUserId(int userId, String status) {
		try {
			String sql = "SELECT * FROM tickets WHERE user_id = ? AND status = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userId);
			pstmt.setString(2, status);
			
			ResultSet rs = pstmt.executeQuery();
			
			List<Ticket> tkList = new ArrayList<Ticket>();
			
			Ticket target = new Ticket();
			
			while(rs.next()) {
				target.setTicketId(rs.getInt("ticket_id"));
				target.setUserId(rs.getInt("user_id"));
				target.setAmount(rs.getInt("amount"));
				target.setDescription(rs.getString("description"));
				target.setStatus(rs.getString("status"));
				tkList.add(target);
				target = new Ticket();
				logger.info("Target added.");
			}
			
			return tkList;
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	@Override
	public boolean update(Ticket ticket) {
		// Setting the ticket status to Accepted or Denied
		try {
			String sql = "UPDATE tickets SET user_id=?, amount=?, description=?, status=? WHERE ticket_id=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, ticket.getUserId());
			pstmt.setInt(2, ticket.getAmount());
			pstmt.setString(3, ticket.getDescription());
			pstmt.setString(4, ticket.getStatus());
			pstmt.setInt(5, ticket.getTicketId());
			
			if (pstmt.executeUpdate() > 0) {
				return true;
			};
			
		} catch (SQLException ex) {
			logger.error("ImpTicketDAO - update() " + ex.getMessage());
		}
		return false;
	}
	/*
	@Override
	public boolean update(User user) {
		// here we are going to update the database for a user
		try {
			
			String sql = "UPDATE users SET username=?, password=?, first_name=?, last_name=?, birthdate=? WHERE id=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1,user.getUsername());
			pstmt.setString(2, user.getPassword());
			pstmt.setString(3,user.getFirstName());
			pstmt.setString(4, user.getLastName());
			pstmt.setDate(5, Date.valueOf(user.getBirthdate()));
			pstmt.setInt(6, user.getId());
			
			if(pstmt.executeUpdate() > 0) {
				return true;
			};
			
		} catch (SQLException sqlEx){
			logger.error("UserDAOImpl - update() " + sqlEx.getMessage());
		}
		
		return false;
	}
	*/

	@Override
	public boolean deleteByTicketId(int ticketId) {
		// TODO Auto-generated method stub
		return false;
	}

}
