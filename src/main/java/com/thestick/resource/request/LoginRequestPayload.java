package com.thestick.resource.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class LoginRequestPayload {
	@SuppressWarnings("mutable")
	public abstract char[] getPassword();
	
	@JsonCreator
	public static LoginRequestPayload create(@JsonProperty("password") char[] password) {
		return new AutoValue_LoginRequestPayload(password);
	}
}
