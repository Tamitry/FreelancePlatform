package by.tarlikovski.freelance.service.impl;

import by.tarlikovski.freelance.service.PasswordEncoder;
import by.tarlikovski.freelance.service.ServiceException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public String encode(final String pass)
            throws ServiceException {
        /*SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        for (byte v : salt) {
            v = (byte) Math.abs(v);
        }
        KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 128);
        byte[] hash;
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ServiceException(e);
        }
        String str = new String(salt) + ":" + new String(hash);
        return str;*/
        return pass;
    }

    @Override
    public String encode(final String pass,final String s)
            throws ServiceException {
        /*
        byte[] salt = s.getBytes();
        KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 128);
        byte[] hash;
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            hash = factory.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new ServiceException(e);
        }
        return new String(salt, StandardCharsets.UTF_8) + ":" + new String(hash, StandardCharsets.UTF_8);*/
        return pass;
    }
}
