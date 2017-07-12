package com.thestick.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Comment {
	@JsonProperty("user") public abstract String username();
	@JsonProperty("thread") public abstract LocalDateTime timestamp();
	@JsonProperty("contents") public abstract String contents();
	@JsonProperty("threadId") public abstract UUID threadId();
	
	@JsonCreator
	public static Comment create(String username, LocalDateTime timestamp, String contents, UUID threadId) {
		return new AutoValue_Comment(username, timestamp, contents, threadId);
	}
}
