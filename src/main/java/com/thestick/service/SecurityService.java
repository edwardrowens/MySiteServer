package com.thestick.service;

import com.thestick.model.SaltHash;

public interface SecurityService {
	SaltHash hashPassword(char[] password);
	boolean authenticate(char[] password, SaltHash saltHash);
}
