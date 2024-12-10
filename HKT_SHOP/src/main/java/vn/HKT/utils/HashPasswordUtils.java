package vn.HKT.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashPasswordUtils {
	public static String hashPasswordWithSHA256(String plainPassword) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(plainPassword.getBytes());
			StringBuilder hexString = new StringBuilder(64);

			for (byte b : hash) {
				hexString.append(String.format("%02x", b));
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}