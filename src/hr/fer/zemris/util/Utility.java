package hr.fer.zemris.util;

final public class Utility {

    private Utility() {
    }

    /**
     * Returns byte representation of hex-encoded {@link String}.
     * 
     * @param s string to decode
     * @return byte representation
     */
    public static byte[] hexToByte(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i + 1 < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * Returns string representation of a byte array.
     * 
     * @param bytes bytes to represent
     * @return string representation
     */
    public static String byteToHex(byte[] bytes) {
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            buff.append(Integer.toHexString(bytes[i] & 0xff));
        }
        return buff.toString();
    }

}
