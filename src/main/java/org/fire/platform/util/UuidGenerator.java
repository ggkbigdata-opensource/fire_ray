package org.fire.platform.util;

import org.apache.commons.codec.binary.Base64;

import java.security.SecureRandom;

public class UuidGenerator {
    private static final SecureRandom numberGenerator = new SecureRandom(String.valueOf(System.currentTimeMillis()).getBytes());

	public static String next() {
		byte[] data = new byte[16];
		numberGenerator.nextBytes(data);
		return Base64.encodeBase64URLSafeString(data);
	}
}
