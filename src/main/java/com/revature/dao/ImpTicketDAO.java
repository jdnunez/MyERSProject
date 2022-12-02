package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Ticket;
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
	public boolean update(Ticket ticket) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteByTicketId(int ticketId) {
		// TODO Auto-generated method stub
		return false;
	}

}
