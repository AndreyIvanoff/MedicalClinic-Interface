package ru.hospital.utils;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author a.yakischik
 */
public class Encrypter {

	private static String algorithm = "DES";
	private static Key key = null;
	private static Cipher cipher = null;

	private static final String SECRET_KEY = "7gh%9hf-";

	private static  void setUp() throws Exception {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(algorithm);
		key = keyFactory.generateSecret(new DESKeySpec(SECRET_KEY.getBytes("utf-8")));
		cipher = Cipher.getInstance(algorithm);
	}
//------------------------------------//
	public static byte[] encrypt(String input){
		try {
			if (key == null || cipher == null){
				setUp();
			}
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] inputBytes = input.getBytes();
			return cipher.doFinal(inputBytes);
		} catch (Exception e) {
			return null;
		}
	}
//------------------------------------//
	public static String decrypt(byte[] encryptionBytes){
		try {
			if (key == null || cipher == null) {
				setUp();
			}
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] recoveredBytes = cipher.doFinal(encryptionBytes);
			String recovered = new String(recoveredBytes);
			return recovered;
		} catch (Exception e) {
			return null;
		}
	}
//------------------------------------//
}

