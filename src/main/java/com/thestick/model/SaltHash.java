package com.thestick.model;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class SaltHash {
	public abstract String getSalt();
	public abstract String getHash();
	
	public static SaltHash create(String salt, String hash) {
		return new AutoValue_SaltHash(salt, hash);
	}
}
