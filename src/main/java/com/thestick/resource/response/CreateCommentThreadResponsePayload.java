package com.thestick.resource.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class CreateCommentThreadResponsePayload {
	@JsonProperty("id") public abstract long id();
	
	@JsonCreator
	public static CreateCommentThreadResponsePayload create(long id) {
		return new AutoValue_CreateCommentThreadResponsePayload(id);
	}
}
