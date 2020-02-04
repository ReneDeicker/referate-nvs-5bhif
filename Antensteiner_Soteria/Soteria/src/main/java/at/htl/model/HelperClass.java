package at.htl.model;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class HelperClass {
    public static byte[] generateSalt () throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        return salt;
    }

    public static byte[]generateHashedPassword(char[] password,byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        return SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(new PBEKeySpec(password,salt,2048,32*8)).getEncoded();
    }
}
