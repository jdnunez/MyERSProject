package com.revature.models;

import java.util.Objects;

public class Ticket {

	private int ticketId; // ticket ticketId
	private int userId; // user ticketId
	private int amount;
	private String description;
	private String status;
	
	// No-args
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// All-args
	public Ticket(int ticketId, int userId, int amount, String description, String status) {
		super();
		this.ticketId = ticketId;
		this.userId = userId;
		this.amount = amount;
		this.description = description;
		this.status = status;
	}

	// Creation
	public Ticket(int userId, int amount, String description, String status) {
		super();
		this.userId = userId;
		this.amount = amount;
		this.description = description;
		this.status = status;
	}

	// Getters/Setters
	public int getTicketId() {
		return ticketId;
	}
	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	// Hashcode
	@Override
	public int hashCode() {
		return Objects.hash(amount, description, status, ticketId, userId);
	}

	// Equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return amount == other.amount && Objects.equals(description, other.description)
				&& Objects.equals(status, other.status) && ticketId == other.ticketId && userId == other.userId;
	}
	
	
	// To String
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", userId=" + userId + ", amount=" + amount + ", description="
				+ description + ", status=" + status + "]";
	}
	
	
	
	
}