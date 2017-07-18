package com.thestick.service;

import com.thestick.model.User;

public interface UserService {
	User createUser(String username, char[] password);
	boolean authenticateUser(String username, char[] password);
}
