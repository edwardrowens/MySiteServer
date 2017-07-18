package com.thestick.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Service;

import com.thestick.model.SaltHash;

@Service
public class SecurityServiceImpl implements SecurityService {
	
	public static final String ALGORITHM = "PBKDF2WithHmacSHA1";
	public static final int KEY_LENGTH = 128;
	public static final int ITERATIONS = 65536;

	@Override
	public SaltHash hashPassword(char[] password) {
		byte[] salt = new byte[KEY_LENGTH / 8];
		new SecureRandom().nextBytes(salt);
		byte[] hash = generateHash(password, salt);
		blatPassword(password);
		Base64.Encoder enc = Base64.getEncoder();
		return SaltHash.create(enc.encodeToString(salt), enc.encodeToString(hash));
	}

	@Override
	public boolean authenticate(char[] password, SaltHash saltHash) {
	    byte[] hash = generateHash(password, Base64.getUrlDecoder().decode(saltHash.getSalt()));
	    blatPassword(password);
	    return Base64.getEncoder().encodeToString(hash).equals(saltHash.getHash());
	}
	
	private byte[] generateHash(char[] password, byte[] salt) {
		KeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
		SecretKeyFactory f = null;
		byte[] hash;
		try {
			f = SecretKeyFactory.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("Missing algorithm: " + ALGORITHM, e);
		}
		try {
			hash = f.generateSecret(spec).getEncoded();
		} catch (InvalidKeySpecException e) {
			throw new IllegalStateException("Invalid SecretKeyFactory", e);
		}
		return hash;
	}
	
	private void blatPassword(char[] password) {
		for (int i = 0; i < password.length; ++i) {
			password[i] = 'a';
		}
	}
}
