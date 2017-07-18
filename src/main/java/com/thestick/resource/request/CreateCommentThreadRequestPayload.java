package com.thestick.resource.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class CreateCommentThreadRequestPayload {
	public abstract String getCreator();
	
	@JsonCreator
	public static CreateCommentThreadRequestPayload create(@JsonProperty("creator") String creator) {
		return new AutoValue_CreateCommentThreadRequestPayload(creator);
	}
}
