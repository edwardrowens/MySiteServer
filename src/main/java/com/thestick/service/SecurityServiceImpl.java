package com.thestick.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {
	
	public static final String ALGORITHM = "PBKDF2WithHmacSHA1";
	public static final int KEY_LENGTH = 128;
	public static final int ITERATIONS = 1000;
	public static final String ID = "@VF7@";
	private static final Pattern layout = Pattern.compile("@VF7@(\\d\\d\\d\\d?)@(.{44})");

	@Override
	public String hashPassword(char[] password) {
		byte[] salt = new byte[KEY_LENGTH / 8];
		new SecureRandom().nextBytes(salt);
		byte[] hash = generateHash(password, salt, ITERATIONS);
		blatPassword(password);
		Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
		return ID + ITERATIONS + '@' + enc.encodeToString(salt) + enc.encodeToString(hash);
	}

	@Override
	public boolean authenticate(char[] password, String token) {
		Matcher matcher = layout.matcher(token);
		if (!matcher.matches()) {
			return false;
		}
	    int iterations = Integer.parseInt(matcher.group(1));
	    byte[] hash = Base64.getUrlDecoder().decode(matcher.group(2));
	    byte[] salt = Arrays.copyOfRange(hash, 0, KEY_LENGTH / 8);
	    byte[] check = generateHash(password, salt, iterations);
	    Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding();
	    return token.substring(token.length() - 22).equals(enc.encodeToString(check));
	}
	
	private byte[] generateHash(char[] password, byte[] salt, int iterations) {
		KeySpec spec = new PBEKeySpec(password, salt, iterations, KEY_LENGTH);
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
