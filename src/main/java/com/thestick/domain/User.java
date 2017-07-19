package com.thestick.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	private String username;
	private String token;
	
	protected User() {}
	
	public User(String username, String token) {
		this.username = username;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public String getToken() {
		return token;
	}
}
