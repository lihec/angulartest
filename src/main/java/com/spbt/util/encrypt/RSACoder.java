package com.spbt.util.encrypt;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
/**
 * RSA工具类
 * @author lihe
 *
 */
public abstract class RSACoder {
	
	public static final String KEY_ALGORITHM = "RSA";

	private static final String PUBLIC_KEY = "RSAPublicKey";
	
	private static final String PRIVATE_KEY = "RSAPrivateKey";
	
	private static final int KEY_SIZE = 1024;
	
	/**
	 * 私钥解密
	 * @param data
	 * @param key
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.spec.InvalidKeySpecException
	 * @throws javax.crypto.NoSuchPaddingException
	 * @throws java.security.InvalidKeyException
	 * @throws javax.crypto.IllegalBlockSizeException
	 * @throws javax.crypto.BadPaddingException
	 */
	public static byte[] decryptByPrivateKey(byte[] data,byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 公钥解密
	 * @param data
	 * @param key
	 * @return
	 * @throws java.security.InvalidKeyException
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.spec.InvalidKeySpecException
	 * @throws javax.crypto.NoSuchPaddingException
	 * @throws javax.crypto.IllegalBlockSizeException
	 * @throws javax.crypto.BadPaddingException
	 */
	public static byte[] decryptByPublicKey(byte[] data,byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		X509EncodedKeySpec x509EncodedKeySpec =  new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 公钥加密
	 * @param data
	 * @param key
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.spec.InvalidKeySpecException
	 * @throws javax.crypto.NoSuchPaddingException
	 * @throws java.security.InvalidKeyException
	 * @throws javax.crypto.IllegalBlockSizeException
	 * @throws javax.crypto.BadPaddingException
	 */
	public static byte[] encryptByPublicKey(byte[] data,byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		X509EncodedKeySpec x509EncodedKeySpec =  new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 私钥加密
	 * @param data
	 * @param key
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws java.security.spec.InvalidKeySpecException
	 * @throws javax.crypto.NoSuchPaddingException
	 * @throws java.security.InvalidKeyException
	 * @throws javax.crypto.IllegalBlockSizeException
	 * @throws javax.crypto.BadPaddingException
	 */
	public static byte[] encryptByPrivateKey(byte[] data,byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
	
	/**
	 * 从keyMap中获取私钥
	 * @param keyMap
	 * @return
	 */
	public static byte[] getPrivateKey(Map<String,Object> keyMap){
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return key.getEncoded();
	}
	
	/**
	 * 从keyMap中获取公钥
	 * @param keyMap
	 * @return
	 */
	public static byte[] getPublicKey(Map<String,Object> keyMap){
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return key.getEncoded();
	}
	
	/**
	 * 生成密钥keyMap
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 */
	public static Map<String,Object> initKey() throws NoSuchAlgorithmException{
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(KEY_SIZE);
		//生成密钥对
		KeyPair keyPair = keyPairGen.generateKeyPair();
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate(); 
		Map<String,Object> keyMap = new HashMap<String,Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}
	
	
	
	

}