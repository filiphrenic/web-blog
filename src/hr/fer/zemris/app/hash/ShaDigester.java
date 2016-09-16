package hr.fer.zemris.app.hash;

import hr.fer.zemris.util.Utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class is used for calculating SHA-1 file digest.
 * 
 * @author Filip Hrenić
 * @version 1.0
 */
public class ShaDigester {

    private MessageDigest shaDigest;
    private byte[] digest;
    private final String text;

    /**
     * Creates a new ShaChecker that calculates SHA-1 file digest of given text.
     * 
     * @param text
     */
    public ShaDigester(String text) {
        this.text = text;

        try {
            shaDigest = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException nsae) {
            // ignoring it, because there is a SHA-1 algorithm
        }
    }

    /**
     * Calculates the digest that {@link MessageDigest} digested.
     * 
     * @return this object
     */
    public ShaDigester calculateDigest() {
        byte[] buffer = Utility.hexToByte(text);
        shaDigest.update(buffer);
        this.digest = shaDigest.digest();
        return this;
    }

    /**
     * Returns the calculated digest.
     * 
     * @return string representation of the digest
     */
    public String getDigest() {
        return Utility.byteToHex(digest);
    }

    /**
     * @return digest's bytes
     */
    public byte[] getDigestBytes() {
        return digest;
    }

    /**
     * Checks if the two digests are the same.
     * 
     * @param keyText text being compared to
     * @return <code>true</code> if they are the same, <code>false</code> otherwise
     */
    public boolean checkIfAreTheSame(String keyText) {
        return Utility.byteToHex(digest).equals(keyText);
    }

}
