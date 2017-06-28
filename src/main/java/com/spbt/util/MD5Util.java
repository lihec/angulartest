package com.spbt.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Static functions to simplifiy common {@link java.security.MessageDigest}
 * tasks. This class is thread safe.
 * 
 * @author 99bill
 */
public final class MD5Util {
    /**记录日志*/
    private static Logger logger = LoggerFactory.getLogger(MD5Util.class);

    /**
     * 工具类采用私有构造
     * 
     * @author 李贺[of253]
     * @date 2013-12-6 下午3:46:16
     */
    private MD5Util() {
    }

    /**
     * Returns a MessageDigest for the given <code>algorithm</code>.
     * 
     *            The MessageDigest algorithm name.
     * @return An MD5 digest instance.
     * @throws RuntimeException
     *             when a {@link java.security.NoSuchAlgorithmException} is
     *             caught
     */

    static MessageDigest getDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.toString(), e);
            return null;
        }
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element
     * <code>byte[]</code>.
     * 
     * @param data
     *            Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(byte[] data) {
        return getDigest().digest(data);
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element
     * <code>byte[]</code>.
     * 
     * @param data
     *            Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(String data, String charset) {
        if (charset == null) {
            return md5(data.getBytes());
        } else {
            try {
                return md5(data.getBytes(charset));
            } catch (UnsupportedEncodingException e) {
                logger.error("编码错误", e);
            }
            return null;
        }
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex
     * string.
     * 
     * @param data
     *            Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(byte[] data) {
        return HexUtil.toHexString(md5(data));
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex
     * string.
     * 
     * @param data
     *            Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(String data) {
        return HexUtil.toHexString(md5(data, null));
    }

    /**
     * 指定编码加密
     * 
     * @author lihe 2013-7-4 下午5:27:07
     * @param data
     * @param charset
     * @return
     * @see
     * @since
     */
    public static String md5Hex(String data, String charset) {
        return HexUtil.toHexString(md5(data, charset));
    }

}
