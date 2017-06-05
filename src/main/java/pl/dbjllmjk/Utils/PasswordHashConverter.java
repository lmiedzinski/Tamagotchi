package pl.dbjllmjk.Utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Class containing password hashing utilities.
 */
public class PasswordHashConverter {

    private PasswordHashConverter() {
    }

    /**
     * Checks whether provided credentials are correct
     * @param login User's login
     * @param password User's password
     * @param hash User's hash
     * @return true if correct
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException 
     */
    public static boolean checkPassword(String login, String password, String hash) throws UnsupportedEncodingException, NoSuchAlgorithmException{
        return PasswordHashConverter.hashPassword(login, password).equals(hash);
    }
    
    /**
     * Generates hash of a password
     * @param login User's login
     * @param password User's password
     * @return hash of a password
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException 
     */
    public static String hashPassword(String login, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String hash = PasswordHashConverter.hashStep(password, 0);
        for (int i = 0; i < 3; i++) {
            hash = PasswordHashConverter.mixHash(login, hash, i);
            hash = PasswordHashConverter.hashStep(hash, i);
        }
        return hash;
    }

    /**
     * Performs magic mixing
     * @param login User's login
     * @param hash User's password
     * @param round Round number
     * @return Temporary hash
     */
    private static String mixHash(String login, String hash, int round) {
        String mixedHash = hash;
        switch (round) {
            case 0: {
                mixedHash = login + hash;
                break;
            }
            case 1: {
                mixedHash += login;
                break;
            }
            case 2: {
                mixedHash = new StringBuilder(mixedHash).reverse().toString();
                break;
            }
        }
        return mixedHash;
    }

    /**
     * Performs hashing
     * @param password User's password to hash
     * @param round Round number
     * @return Hash of a password
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException 
     */
    private static String hashStep(String password, int round) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-" + Integer.toString(PasswordHashConverter.calcAlgorithm(round)));
        md.update(password.getBytes("UTF-8"));
        byte[] digest = md.digest();
        password = String.format("%064x", new java.math.BigInteger(1, digest));
        return password;
    }

    /**
     * Calculate hashing algorithm
     * @param round Round number
     * @return Number of bits
     */
    private static int calcAlgorithm(int round) {
        return (int) Math.pow(2, 8 + ((round + 1) % 2));
    }
}
