package com.thestick.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class ErrorResponsePayload {
	@JsonProperty("error") public abstract String error();
	
	public static ErrorResponsePayload create(String error) {
		return new AutoValue_ErrorResponsePayload(error);
	}
}
