package com.revature.models;

import java.util.Objects;

public class User {
	
	private int userId;
	private String username;
	private String password;
	private String role;
//	private Boolean isManager;
	
	// no-args
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	// all-args
	public User(int userId, String username, String password, String role) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	// creation
	public User(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}

	// getters/setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	// hashCode
	@Override
	public int hashCode() {
		return Objects.hash(userId, role, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return userId == other.userId && Objects.equals(role, other.role) && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

	// toString
	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", role=" + role
				+ "]";
	}

	
}
