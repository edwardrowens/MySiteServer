package com.thestick.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="comment_threads")
public class CommentThread {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@Column(columnDefinition="timestamp")
	private LocalDateTime created;
	private String creator;
	
	protected CommentThread() {
		
	}
	
	public CommentThread(LocalDateTime created, String creator) {
		this.created = created;
		this.creator = creator;
	}
	
	public long getId() {
		return id;
	}
	
	public LocalDateTime getCreated() {
		return created;
	}
	
	public String getCreator() {
		return creator;
	}
}
