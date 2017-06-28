package com.spbt.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.bouncycastle.util.encoders.Hex;
/**
 * MD5工具类
 * @author lihe
 *
 */
public abstract class MD5Coder {
	/**
	 * 字符串编码
	 */
	public static final String ENCODING = "UTF-8";

	/**
	 * md5编码
	 * @param data
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 */
	public static byte[] md5(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		return digest.digest(data);
	}

	/**
	 * md5编码（字符串采用UTF-8编码）
	 * @param data
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.io.UnsupportedEncodingException
	 */
	public static byte[] md5(String data) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		return digest.digest(data.getBytes(ENCODING));
	}

	/**
	 * md5编码后转换成HexString
	 * @param data
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 */
	public static String md5Hex(byte[] data) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		return Hex.toHexString(digest.digest(data));
	}

	/**
	 * md5编码后转换成HexString（字符串采用UTF-8编码）
	 * @param data
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.io.UnsupportedEncodingException
	 */
	public static String md5Hex(String data) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		return Hex.toHexString(digest.digest(data.getBytes(ENCODING)));
	}

}
