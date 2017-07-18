package com.thestick.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class CommentThread {
	@JsonProperty("id") public abstract long getId();
	@JsonProperty("created") public abstract LocalDateTime getCreated();
	@JsonProperty("creator") public abstract String getCreator();
	
	@JsonCreator
	public static CommentThread create(long id, LocalDateTime created, String creator) {
		return new AutoValue_CommentThread(id, created, creator);
	}
}
