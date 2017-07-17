package com.thestick.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Comment {
	@JsonProperty("id") public abstract long id();
	@JsonProperty("user") public abstract String username();
	@JsonProperty("thread") public abstract LocalDateTime timestamp();
	@JsonProperty("contents") public abstract String contents();
	@JsonProperty("threadId") public abstract long threadId();
	
	@JsonCreator
	public static Comment create(long id, String username, LocalDateTime timestamp, String contents, long threadId) {
		return new AutoValue_Comment(id, username, timestamp, contents, threadId);
	}
}
