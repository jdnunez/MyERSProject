package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
		// TODO Auto-generated method stub
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
