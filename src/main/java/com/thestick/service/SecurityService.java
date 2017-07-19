package com.thestick.service;

public interface SecurityService {
	String hashPassword(char[] password);
	boolean authenticate(char[] password, String token);
}
