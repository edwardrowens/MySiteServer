package com.thestick.resource.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class CreateCommentResponsePayload {
	@JsonProperty("commentId") public abstract long commentId();
	
	@JsonCreator
	public static CreateCommentResponsePayload create(long commentId) {
		return new AutoValue_CreateCommentResponsePayload(commentId);
	}
}
