package com.thestick.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class User {
	@JsonProperty("username") public abstract String getUsername();
	@JsonProperty("hash") public abstract String getHash();
	@JsonProperty("salt") public abstract String getSalt();
	
	@JsonCreator
	public static User create(String username, String hash, String salt) {
		return new AutoValue_User(username, hash, salt);
	}
}
