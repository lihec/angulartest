package com.spbt.util.encrypt;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
/**
 * IDEA编码，需要采用BouncyCastle提供的加密类
 * @author lihe
 *
 */
public abstract class IDEACoder {
	
	public static final String KEY_ALGORITHM = "IDEA";
	public static final String CIPHER_ALGORITHM = "IDEA/ECB/ISO10126Padding";

	/**
	 * 转换密钥
	 * 
	 * @param key
	 * @return
	 */
	public static Key toKey(byte[] key) {
		SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
		return secretKey;
	}

	/**
	 * 解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws javax.crypto.NoSuchPaddingException
	 * @throws java.security.InvalidKeyException
	 * @throws javax.crypto.IllegalBlockSizeException
	 * @throws javax.crypto.BadPaddingException
	 */
	public static byte[] descrypt(byte[] data, byte[] key)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		//加入BouncyCastleProvider
		Security.addProvider(new BouncyCastleProvider());
		// 还原密钥
		Key k = toKey(key);
		// 实例化
//		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM,provider);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}

	/**
	 * 加密
	 * @param data
	 * @param key
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 * @throws javax.crypto.NoSuchPaddingException
	 * @throws java.security.InvalidKeyException
	 * @throws javax.crypto.IllegalBlockSizeException
	 * @throws javax.crypto.BadPaddingException
	 */
	public static byte[] encrypt(byte[] data, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		//加入BouncyCastleProvider
		Security.addProvider(new BouncyCastleProvider());
		// 还原密钥
		Key k = toKey(key);
		// 实例化
//		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM,provider);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}
	
	/**
	 * 生成密钥
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 */
	public static byte[] initKey() throws NoSuchAlgorithmException{
		//加入BouncyCastleProvider
		Security.addProvider(new BouncyCastleProvider());
		//实例化,需要引入Bouncy Castle的基础包和ext包2个jar包
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		//IDEA要求密钥长度为128位
		kg.init(128);
		//生成密钥
		SecretKey secretKey = kg.generateKey();
		//获取密钥的二进制编码形式
		return secretKey.getEncoded();
	}
}
