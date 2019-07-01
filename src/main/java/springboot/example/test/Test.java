<<<<<<< Updated upstream
package springboot.example.test;

import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Test {
	private static final String SHA1 = "SHA-1";
	private static final int HASH_ITERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	private static SecureRandom random = new SecureRandom();
	private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f'};

	private static byte[] generateSalt(int numBytes) {
		byte[] bytes = new byte[numBytes];
		random.nextBytes(bytes);
		return bytes;
	}

	private static byte[] digest(byte[] input, byte[] salt) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			if (salt != null)
				md.update(salt);
			byte[] result = md.digest(input);
			for (int i = 1; i < 1024; ++i) {
				md.reset();
				result = md.digest(result);
			}
			return result;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private static char[] encodeHex(byte[] data) {
		int l = data.length;
		char[] out = new char[l << 1];
		int i = 0;
		for (int j = 0; i < l; ++i) {
			out[(j++)] = DIGITS_LOWER[((0xF0 & data[i]) >>> 4)];
			out[(j++)] = DIGITS_LOWER[(0xF & data[i])];
		}
		return out;
	}

	private static byte[] decodeHex(char[] data) {
		int len = data.length;
		if ((len & 0x1) != 0) {
			throw new RuntimeException("Odd number of characters.");
		}
		byte[] out = new byte[len >> 1];
		int i = 0;
		for (int j = 0; j < len; ++i) {
			int f = Character.digit(data[j], 16) << 4;
			++j;
			f |= Character.digit(data[j], 16);
			++j;
			out[i] = (byte) (f & 0xFF);
		}
		return out;
	}

	public static String encrypt(String message) {
		byte[] salt = generateSalt(8);
		byte[] hashMessage = digest(message.getBytes(), salt);
		return new String(encodeHex(salt)) + new String(encodeHex(hashMessage));
	}

	public static boolean validate(String plainMessage, String cipherMessage) {
		int len = 16;
		if ((cipherMessage == null) || (cipherMessage.length() < len))
			return false;
		byte[] salt = decodeHex(cipherMessage.substring(0, len).toCharArray());
		byte[] hashMessage = digest(plainMessage.getBytes(), salt);
		String expectedMessage = new String(encodeHex(salt)) + new String(encodeHex(hashMessage));
		return cipherMessage.equals(expectedMessage);
	}

	public static void main(String[] args) {
		String encrypt = encrypt("sakjfj56s5v1saw666d13045670010");
		System.out.println(encrypt);
		System.out.println(
				validate("sakjfj56s5v1saw666d18609891239", "299b0bf3442ce6e231b3b8328b7526b1025e4b9e16db53a60ace21c9"));
	}
=======
package springboot.example.test;

import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Test {
	private static final String SHA1 = "SHA-1";
	private static final int HASH_ITERATIONS = 1024;
	private static final int SALT_SIZE = 8;
	private static SecureRandom random = new SecureRandom();
	private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f'};

	private static byte[] generateSalt(int numBytes) {
		byte[] bytes = new byte[numBytes];
		random.nextBytes(bytes);
		return bytes;
	}

	private static byte[] digest(byte[] input, byte[] salt) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			if (salt != null)
				md.update(salt);
			byte[] result = md.digest(input);
			for (int i = 1; i < 1024; ++i) {
				md.reset();
				result = md.digest(result);
			}
			return result;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	private static char[] encodeHex(byte[] data) {
		int l = data.length;
		char[] out = new char[l << 1];
		int i = 0;
		for (int j = 0; i < l; ++i) {
			out[(j++)] = DIGITS_LOWER[((0xF0 & data[i]) >>> 4)];
			out[(j++)] = DIGITS_LOWER[(0xF & data[i])];
		}
		return out;
	}

	private static byte[] decodeHex(char[] data) {
		int len = data.length;
		if ((len & 0x1) != 0) {
			throw new RuntimeException("Odd number of characters.");
		}
		byte[] out = new byte[len >> 1];
		int i = 0;
		for (int j = 0; j < len; ++i) {
			int f = Character.digit(data[j], 16) << 4;
			++j;
			f |= Character.digit(data[j], 16);
			++j;
			out[i] = (byte) (f & 0xFF);
		}
		return out;
	}

	public static String encrypt(String message) {
		byte[] salt = generateSalt(8);
		byte[] hashMessage = digest(message.getBytes(), salt);
		return new String(encodeHex(salt)) + new String(encodeHex(hashMessage));
	}

	public static boolean validate(String plainMessage, String cipherMessage) {
		int len = 16;
		if ((cipherMessage == null) || (cipherMessage.length() < len))
			return false;
		byte[] salt = decodeHex(cipherMessage.substring(0, len).toCharArray());
		byte[] hashMessage = digest(plainMessage.getBytes(), salt);
		String expectedMessage = new String(encodeHex(salt)) + new String(encodeHex(hashMessage));
		return cipherMessage.equals(expectedMessage);
	}

	public static void main(String[] args) {
		String encrypt = encrypt("sakjfj56s5v1saw666d13045670010");
		System.out.println(encrypt);
		System.out.println(
				validate("sakjfj56s5v1saw666d18609891239", "299b0bf3442ce6e231b3b8328b7526b1025e4b9e16db53a60ace21c9"));
	}
>>>>>>> Stashed changes
}