package com.thestick.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Comment {
	@JsonProperty("id") public abstract long getId();
	@JsonProperty("username") public abstract String getUsername();
	@JsonProperty("created") public abstract LocalDateTime getTimestamp();
	@JsonProperty("contents") public abstract String getContents();
	@JsonProperty("commentThreadId") public abstract long getCommentThreadId();
	
	@JsonCreator
	public static Comment create(long id, String username, LocalDateTime timestamp, String contents, long commentThreadId) {
		return new AutoValue_Comment(id, username, timestamp, contents, commentThreadId);
	}
}
