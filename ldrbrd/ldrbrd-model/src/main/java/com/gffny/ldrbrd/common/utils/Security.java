package com.gffny.ldrbrd.common.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author John Gaffney (john@gffny.com) Dec 24, 2012
 */
public class Security {

	/**
	 * @param string
	 * @return
	 */
	public static String encrypt(String string) {
		try {
			Cipher cipher = getCipher();

			cipher.init(Cipher.ENCRYPT_MODE, getKey());

			byte[] encrypted = cipher.doFinal(string.getBytes());

			return asHex(encrypted);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException
				| IllegalBlockSizeException | BadPaddingException ex) {
			return null;
		}
	}

	/**
	 * @param string
	 * @return
	 */
	public static String decrypt(String string) {
		try {
			Cipher cipher = getCipher();

			cipher.init(Cipher.DECRYPT_MODE, getKey());

			byte[] decrypted = cipher.doFinal(hexStringToByteArray(string));
			return new String(decrypted);
		} catch (Throwable ex) {
			return null;
		}
	}

	// 256 bit key
	// private static final String key =
	// "89e9832e71bcc93ce2e873b51b12e0b9558e931e86eeeaebd9a88bd689ea3307";

	// Using 128 bit encryption instead of 512 bit b/c of unlimited key size
	// encryption needs a java update
	private static final String key = "b14d023130b0ddc5b3f31912e021edee";
	private static SecretKeySpec keySpec = null;

	/**
	 * @return
	 * @throws Exception
	 */
	private static SecretKeySpec getKey() throws IllegalArgumentException {
		if (keySpec == null) {
			keySpec = new SecretKeySpec(hexStringToByteArray(key), "AES");
		}

		return keySpec;
	}

	private static Cipher cipher = null;

	/**
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	private static Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
		if (cipher == null) {
			cipher = Cipher.getInstance("AES");
		}

		return cipher;
	}

	/**
	 * @param buf
	 * @return
	 */
	private static String asHex(byte buf[]) {
		StringBuffer strbuf = new StringBuffer(buf.length * 2);
		int i;

		for (i = 0; i < buf.length; i++) {
			if (((int) buf[i] & 0xff) < 0x10)
				strbuf.append("0");

			strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
		}

		return strbuf.toString();
	}

	/**
	 * @param s
	 * @return
	 */
	private static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(
					s.charAt(i + 1), 16));
		}
		return data;
	}
}
