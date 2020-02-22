package at.htl.control;

import at.htl.entity.HashedPassword;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

public class PasswordSecurityUtils {

    private PasswordSecurityUtils() {}

    public static HashedPassword hashPassword(String password) {
        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        ByteSource salt = rng.nextBytes();
        String hashedPassword = new Sha512Hash(password, salt, 1024).toHex();
        return new HashedPassword(salt.toHex(), hashedPassword);
    }

    public static boolean validatePassword(String submittedPassword, HashedPassword storedPassword) {
        ByteSource salt = new SimpleByteSource(fromHex(storedPassword.getSaltHex()));

        String hashToTestString = new Sha512Hash(submittedPassword, salt, 1024).toHex();

        byte[] storedHash = fromHex(storedPassword.getHexPassword());
        byte[] hashToTest = fromHex(hashToTestString);

        return testIfBytesEqual(storedHash, hashToTest);
    }

    private static boolean testIfBytesEqual(byte[] storedHash, byte[] hashToTest) {
        int diff = storedHash.length ^ hashToTest.length;
        for (int i = 0; i < storedHash.length && i < hashToTest.length; i++) {
            diff |= storedHash[i] ^ hashToTest[i];
        }
        return diff == 0;
    }

    private static byte[] fromHex(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

}
