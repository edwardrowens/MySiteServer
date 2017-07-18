package com.thestick.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	@JoinColumn(name="comment_thread_id")
	private CommentThread commentThread;
	
	@ManyToOne
	@JoinColumn(name="username")
	private User user;
	private String contents;
	
	@Column(columnDefinition="timestamp")
	private LocalDateTime created;
	
	protected Comment() {
		
	}
	
	public Comment(User user, LocalDateTime created, String contents, CommentThread commentThread) {
		this.user = user;
		this.created = created;
		this.contents = contents;
		this.commentThread = commentThread;
	}

	public User getUser() {
		return user;
	}

	public LocalDateTime getTimestamp() {
		return created;
	}

	public String getContents() {
		return contents;
	}

	public CommentThread getCommentThread() {
		return commentThread;
	}
	
	public long getId() {
		return id;
	}
}
