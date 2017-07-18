package com.thestick.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;
import com.thestick.model.SaltHash;
import com.thestick.model.User;
import com.thestick.repository.UsersRepository;

@Service
public class UserServiceImpl implements UserService {
	
	private final SecurityService securityService;
	private final UsersRepository usersRepository;

	@Autowired
	public UserServiceImpl(SecurityService securityService,
						   UsersRepository usersRepository) {
		this.securityService = securityService;
		this.usersRepository = usersRepository;
	}
	
	@Override
	@Transactional
	public User createUser(String username, char[] password) {
		com.thestick.domain.User user = usersRepository.findByUsername(username);
		Preconditions.checkArgument(user == null, "Username is taken");
		SaltHash saltHash = securityService.hashPassword(password);
		usersRepository.save(new com.thestick.domain.User(username, saltHash.getHash(), saltHash.getSalt()));
		return User.create(username, saltHash.getHash(), saltHash.getSalt());
	}

	@Override
	public boolean authenticateUser(String username, char[] password) {
		com.thestick.domain.User user = usersRepository.findByUsername(username);
		Preconditions.checkArgument(user != null, "User does not exist");
		return securityService.authenticate(password, SaltHash.create(user.getSalt(), user.getHash()));
	}

}
