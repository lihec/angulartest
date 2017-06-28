package com.spbt.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.util.encoders.UrlBase64;

public class SecTect {
	private static final String CHARSET = "UTF-8";
	
	public static void testSignatrue() throws NoSuchAlgorithmException,
			UnsupportedEncodingException, InvalidKeyException,
			SignatureException {
		byte[] bytes = "signatrue data".getBytes(CHARSET);
		KeyPairGenerator generator = KeyPairGenerator.getInstance("DSA");
		generator.initialize(1024);
		KeyPair keyPair = generator.generateKeyPair();
		Signature signature = Signature.getInstance(generator.getAlgorithm());
		signature.initSign(keyPair.getPrivate());
		signature.update(bytes);
		byte[] sign = signature.sign();

		signature.initVerify(keyPair.getPublic());
		signature.update(bytes);
		System.out.println(signature.verify(sign));
	}

	public static void testProviders() {
		for (Provider p : Security.getProviders()) {
			System.out.println(p);
			for (Map.Entry<Object, Object> entry : p.entrySet()) {
				System.out.println("\t" + entry.getKey() + "="
						+ entry.getValue());
			}
		}
	}
	
	public static void testBase64() throws UnsupportedEncodingException{
		byte[] data = "测试base64编码=?我草/\\".getBytes(CHARSET);
		//sun的没发现解密，不建议使用的类
		sun.misc.BASE64Encoder sunBase64 = new sun.misc.BASE64Encoder();
		String sunEnc = sunBase64.encode(data);
		System.out.println("sunEnc="+sunEnc);
		//url安全编码格式，可以作为url参数传输，也可以直接把byte[]转成hexStirng
		String urlEnc = Base64.encodeBase64URLSafeString(data);
		System.out.println("urlEnc="+urlEnc);
		String apcEnc = Base64.encodeBase64String(data);
		System.out.println("apcEnc="+apcEnc);
		
		
		
		System.out.println(new String(org.bouncycastle.util.encoders.Base64.encode(data),CHARSET));
		System.out.println(new String(UrlBase64.encode(data),CHARSET));
		
//		String pEnc = com.life.jfx.util.Base64.encodeBytes(data);
//		System.out.println("perEnc="+pEnc);
		
		System.out.println("apcDec="+new String(Base64.decodeBase64(apcEnc),CHARSET));
		
		System.out.println("urlDec="+new String(Base64.decodeBase64(urlEnc),CHARSET));
		
//		System.out.println("perDec="+com.life.jfx.util.Base64.decode(pEnc,CHARSET));
		
		String base64Data = Base64Coder.encode("测试base64编码=?我草/\\");
		String base64UrlData = Base64Coder.urlEncode("测试base64编码=?我草/\\");
		System.out.println(base64Data);
		System.out.println(base64UrlData);
		System.out.println(Base64Coder.decode(base64Data));
		System.out.println(Base64Coder.urlDecode(base64UrlData));
	}
	
	public static void testMD5() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String data = "密码是abc123ABC";
		
		//sun
		MessageDigest digest =MessageDigest.getInstance("MD5");
		byte[] bytes=digest.digest(data.getBytes());
		System.out.println("sunEnc="+Hex.toHexString(bytes));
		// apache
		String apcEnc = DigestUtils.md5Hex(data);
		// of
		String perEnc=MD5Coder.md5Hex(data);
		
		System.out.println("apcEnc="+apcEnc);
		System.out.println("perEnc="+perEnc);
	}
	
	public static void testSHA() throws NoSuchAlgorithmException{
		String data = "密码是abc123ABC";
		//sun
		MessageDigest digest =MessageDigest.getInstance("SHA-512");
		byte[] bytes=digest.digest(data.getBytes());
		System.out.println("sunEnc="+Hex.toHexString(bytes));
		//apache
		String apcEnc = DigestUtils.sha512Hex(data);
		System.out.println("apcEnc="+apcEnc);
	}
	
	public static void testAES() throws Exception{
		String data = "密码是abc123ABC";
		byte[] dataByte = data.getBytes(CHARSET);
		
		System.out.println( System.getProperty("file.encoding"));
		System.out.println(dataByte.length);
		System.out.println(data.getBytes().length);
		
//		byte[] key = AESCoder.initKey();
		byte[] key = Hex.decode("869e913fb72c7343a2ac94d12d0ca640f87cc6a0d2b7cc77dc46eab0474fb2c2");		
		String keyString = Hex.toHexString(key);
		System.out.println(keyString);
		System.out.println(Arrays.equals(key, Hex.decode(keyString)));
		byte[] encStr = AESCoder.encrypt(dataByte, key);
		System.out.println("encStr="+Hex.toHexString(encStr));
		System.out.println("encStr="+ Hex.toHexString(encStr));
		byte[] decStr = AESCoder.descrypt(encStr, key);
		System.out.println("decStr="+new String(decStr, CHARSET));


        String keyStr = "3b8RfV1AamJOlgjg+VW4e4RpECQamuwwbAn2P6YYags=";
        System.out.println("明文：" + data);
        String encrypt = AESCoder.encrypt(data, keyStr);
        System.out.println("密文：" + encrypt);
        String descrypt = AESCoder.descrypt(encrypt, keyStr);
        System.out.println("解密后：" + descrypt);
	}
	
	public static void testIDEA()throws Exception{
		String data = "密码是abc123ABC";
		byte[] dataByte = data.getBytes(CHARSET);
		
		byte[] key = IDEACoder.initKey();
		
		byte[] encStr = IDEACoder.encrypt(dataByte, key);
		System.out.println("encStr="+Hex.toHexString(encStr));
		byte[] decStr = IDEACoder.descrypt(encStr, key);
		System.out.println("decStr="+new String(decStr, CHARSET));
	}
	
	
	public static void testRSA() throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
		String data = "密码是abc123ABC";
		byte[] dataByte = data.getBytes(CHARSET);
		
		Map<String, Object> keyMap = RSACoder.initKey();
		byte[] encStr = RSACoder.encryptByPublicKey(dataByte, RSACoder.getPublicKey(keyMap));
		System.out.println("encStr="+Hex.toHexString(encStr));
		byte[] decStr = RSACoder.decryptByPrivateKey(encStr, RSACoder.getPrivateKey(keyMap));
		System.out.println("decStr="+new String(decStr, CHARSET));
		
		byte[] encStr2 = RSACoder.encryptByPrivateKey(dataByte, RSACoder.getPrivateKey(keyMap));
		System.out.println("encStr2="+Hex.toHexString(encStr2));
		byte[] decStr2 = RSACoder.decryptByPublicKey(encStr2, RSACoder.getPublicKey(keyMap));
		System.out.println("decStr2="+new String(decStr2, CHARSET));
		
		
	}
	
	

	public static void main(String[] args) throws Exception {

//		testSignatrue();
		
//		testProviders();
		
//		testBase64();
		
//		testMD5();
		
//		testSHA();
		
		testAES();
		
//		testIDEA();
		
//		testRSA();



	}

}
