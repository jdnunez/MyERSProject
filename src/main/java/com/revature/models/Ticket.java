package com.revature.models;

import java.util.Objects;

public class Ticket {

	private int ticketId; // ticket ticketId
	private int userId; // user ticketId
	private float amount;
	private String desc;
	private String status;
	
	// no-args
	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}

	// all-args
	public Ticket(int ticketId, int userId, float amount, String desc, String status) {
		super();
		this.ticketId = ticketId;
		this.userId = userId;
		this.amount = amount;
		this.desc = desc;
		this.status = status;
	}
	
	// creation
	public Ticket(int userId, float amount, String desc, String status) {
		super();
		this.userId = userId;
		this.amount = amount;
		this.desc = desc;
		this.status = status;
	}

	// getters/setters
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

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// hashCode
	@Override
	public int hashCode() {
		return Objects.hash(amount, desc, ticketId, status, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Float.floatToIntBits(amount) == Float.floatToIntBits(other.amount) && Objects.equals(desc, other.desc)
				&& ticketId == other.ticketId && Objects.equals(status, other.status) && userId == other.userId;
	}
	
	// toString
	@Override
	public String toString() {
		return "Ticket [ticketId=" + ticketId + ", userId=" + userId + ", amount=" + amount + ", desc=" + desc + ", status=" + status
				+ "]";
	}
	
	
	
}
