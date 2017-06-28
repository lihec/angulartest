package com.spbt.util;


/**
 * This class provides convenient functions to convert hex string to byte array
 * and vice versa.
 *
 * @author 99bill
 */
public final class HexUtil {
    private static final int FOUR = 4;
    private static final int SIXTEEN = 16;
    private static final byte BYT = 0x0F;

    private HexUtil() {
    }

    /**
     * Converts a byte array to hex string.
     *
     * @param b -
     *          the input byte array
     * @return hex string representation of b.
     */

    public static String toHexString(byte[] b) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            sb.append(HexUtil.HEX_CHARS.charAt(b[i] >>> FOUR & BYT));
            sb.append(HexUtil.HEX_CHARS.charAt(b[i] & BYT));
        }
        return sb.toString();
    }

    /**
     * Converts a hex string into a byte array.
     *
     * @param s -
     *          string to be converted
     * @return byte array converted from s
     */
    public static byte[] toByteArray(String s) {
        byte[] buf = new byte[s.length() / 2];
        int j = 0;
        for (int i = 0; i < buf.length; i++) {
            buf[i] = (byte) ((Character.digit(s.charAt(j++), SIXTEEN) << FOUR) | Character
                    .digit(s.charAt(j++), SIXTEEN));
        }
        return buf;
    }

    private static final String HEX_CHARS = "0123456789abcdef";

}
