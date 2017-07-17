package com.thestick.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private long threadId;
	private String username;
	private String contents;
	private LocalDateTime timestamp;
	
	protected Comment() {
		
	}
	
	public Comment(String username, LocalDateTime timestamp, String contents, long threadId) {
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

	public long getThreadId() {
		return threadId;
	}
	
	public long getId() {
		return id;
	}
}
