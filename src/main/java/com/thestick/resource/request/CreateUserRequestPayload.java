package com.thestick.resource.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class CreateUserRequestPayload {
	public abstract String getUsername();
	@SuppressWarnings("mutable")
	public abstract char[] getPassword();
	
	@JsonCreator
	public static CreateUserRequestPayload create(@JsonProperty("username") String username,
												  @JsonProperty("password") char[] password) {
		return new AutoValue_CreateUserRequestPayload(username, password);
	}
}
