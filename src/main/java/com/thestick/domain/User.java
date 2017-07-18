package com.thestick.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	private String username;
	private String hash;
	private String salt;
	
	protected User() {}
	
	public User(String username, String hash, String salt) {
		this.username = username;
		this.hash = hash;
		this.salt = salt;
	}

	public String getUsername() {
		return username;
	}

	public String getHash() {
		return hash;
	}


	public String getSalt() {
		return salt;
	}
}
