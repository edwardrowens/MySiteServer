package com.thestick.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comment {
	@Id
	@GeneratedValue
	private long id;
	private String username;
	private LocalDateTime timestamp;
	private String contents;
	private UUID threadId;
	
	Comment() {
		
	}
	
	public Comment(String username, LocalDateTime timestamp, String contents, UUID threadId) {
		this.username = username;
		this.timestamp = timestamp;
		this.contents = contents;
		this.threadId = threadId;
	}

	public String getUsername() {
		return username;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getContents() {
		return contents;
	}

	public UUID getThreadId() {
		return threadId;
	}
}
