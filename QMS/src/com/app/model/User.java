package com.app.model;

public class User {
	private int user_id;
	private String name;
	private String email;
	private String password_hash;
	private String role;
	
	
	
	public User() {
		super();
	}



	public User(int user_id, String name, String email, String password_hash, String role) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.email = email;
		this.password_hash = password_hash;
		this.role = role;
	}



	public int getUser_id() {
		return user_id;
	}



	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword_hash() {
		return password_hash;
	}



	public void setPassword_hash(String password_hash) {
		this.password_hash = password_hash;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + ", email=" + email + ", password_hash=" + password_hash
				+ ", role=" + role + "]";
	}
	
	
	

	
}
