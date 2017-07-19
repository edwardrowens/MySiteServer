package com.thestick.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class User {
	@JsonProperty("username") public abstract String getUsername();
	@JsonProperty("token") public abstract String getToken();
	
	@JsonCreator
	public static User create(String username, String token) {
		return new AutoValue_User(username, token);
	}
}
