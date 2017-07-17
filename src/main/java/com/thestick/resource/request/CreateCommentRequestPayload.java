package com.thestick.resource.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class CreateCommentRequestPayload {
	public abstract String creator();
	public abstract String contents();
	
	@JsonCreator
	public static CreateCommentRequestPayload create(@JsonProperty("creator") String creator,
													 @JsonProperty("contents") String contents) {
		return new AutoValue_CreateCommentRequestPayload(creator, contents);
	}
}
