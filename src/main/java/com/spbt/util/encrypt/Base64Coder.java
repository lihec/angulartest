package com.spbt.util.encrypt;

import java.io.UnsupportedEncodingException;

import org.bouncycastle.util.encoders.Base64;
import org.bouncycastle.util.encoders.UrlBase64;
/**
 * base64编码工具类
 * @author lihe
 *
 */
public abstract class Base64Coder {
	public static final String ENCODING = "UTF-8";
	
	/**
	 * base64编码（字符串采用utf-8编码）
	 * @param data
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 */
	public static String encode(String data) throws UnsupportedEncodingException{
		return new String(Base64.encode(data.getBytes(ENCODING)),ENCODING);
	}
	
	/**
	 * base64编码
	 * @param data
	 * @return
	 */
	public static byte[] encode(byte[] data){
		return Base64.encode(data);
	}
	
	/**
	 * base64解码（字符串采用utf-8编码）
	 * @param data
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 */
	public static String decode(String data) throws UnsupportedEncodingException{
		return new String(Base64.decode(data.getBytes(ENCODING)),ENCODING);
	}
	
	/**
	 * base64解码
	 * @param data
	 * @return
	 */
	public static byte[] decode(byte[] data){
		return Base64.decode(data);
	}
	
	/**
	 * url安全模式base64编码（字符串采用utf-8编码）
	 * @param data
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 */
	public static String urlEncode(String data) throws UnsupportedEncodingException{
		return new String(UrlBase64.encode(data.getBytes(ENCODING)),ENCODING);
	}
	
	/**
	 * url安全模式base64编码
	 * @param data
	 * @return
	 */
	public static byte[] urlEncode(byte[] data){
		return UrlBase64.encode(data);
	}
	
	/**
	 * url安全模式base64解码
	 * @param data
	 * @return
	 */
	public static byte[] urlDecode(byte[] data){
		return UrlBase64.decode(data);
	}
	
	/**
	 * url安全模式base64j解码（字符串采用utf-8编码）
	 * @param data
	 * @return
	 * @throws java.io.UnsupportedEncodingException
	 */
	public static String urlDecode(String data) throws UnsupportedEncodingException{
		return new String(UrlBase64.decode(data.getBytes(ENCODING)),ENCODING);
	}
	
}
