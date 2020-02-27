package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.service.PasswordEncoder;
import by.tarlikovski.freelance.service.ServiceException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class PasswordEncoderImpl implements PasswordEncoder {
    // The higher the number of iterations the more
    // expensive computing the hash is for us and
    // also for an attacker.
    private static final int ITERATIONS = 20*1000;
    private static final int SALT_LEN = 32;
    private static final int DESIRED_KEY_LEN = 256;

    /** Computes a salted PBKDF2 hash of given plaintext password
     suitable for storing in a database.
     Empty passwords are not supported. */
    public String getSaltedHash(final String password) throws ServiceException {
        try {
            byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(SALT_LEN);
            // store the salt with the password
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(salt) + "$" + hash(password, salt);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    /** Checks whether given plaintext password corresponds
     to a stored salted hash of the password. */
    public boolean check(final String password,
                                final String stored)
            throws ServiceException {
        try {
            String[] saltAndPass = stored.split("\\$");
            if (saltAndPass.length != 2) {
                throw new IllegalStateException(
                        "The stored password have the form 'salt$hash'");
            }
            Base64.Decoder decoder = Base64.getDecoder();
            String hashOfInput = hash(password, decoder.decode(saltAndPass[0]));
            return hashOfInput.equals(saltAndPass[1]);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    private String hash(final String password,
                               final byte[] salt)
            throws ServiceException {
        try {
            if (password == null || password.length() == 0)
                throw new IllegalArgumentException("Empty passwords are not supported.");
            SecretKeyFactory f = null;
                f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            SecretKey key = f.generateSecret(new PBEKeySpec(
                    password.toCharArray(), salt, ITERATIONS, DESIRED_KEY_LEN)
            );
            Base64.Encoder encoder = Base64.getEncoder();
            return encoder.encodeToString(key.getEncoded());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ServiceException(e);
        }
    }
}
