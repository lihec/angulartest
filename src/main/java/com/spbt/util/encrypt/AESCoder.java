package com.spbt.util.encrypt;

import org.bouncycastle.util.encoders.UrlBase64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES安全编码工具类
 * 
 * @author lihe
 * 
 */
public abstract class AESCoder {

	public static final String KEY_ALGORITHM = "AES";
	public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
	public static final String CHARSET_UTF8 = "UTF-8";

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
		// 还原密钥
		Key k = toKey(key);
		// 实例化，，可以采用BouncyCastle提供的加密方法
		// Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM,new BouncyCastleProvider());
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}

	/**
	 * 加密
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
	public static byte[] encrypt(byte[] data, byte[] key)
			throws NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		// 还原密钥
		Key k = toKey(key);
		// 实例化，可以采用BouncyCastle提供的加密方法
//		 Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM,new BouncyCastleProvider());
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}

    /**
     * 通过base64密钥AES解密base64加密串，是下面encrypt(String,String)的解密过程
     * @param base64Data AES加密后，base64编码字符串
     * @param base64Key base64编码的密钥
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidKeyException
     * @throws javax.crypto.IllegalBlockSizeException
     * @throws javax.crypto.BadPaddingException
     * @throws java.io.UnsupportedEncodingException
     */
    public static String descrypt(String base64Data, String base64Key)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] key = Base64Coder.decode(base64Key.getBytes(CHARSET_UTF8));
        byte[] data = UrlBase64.decode(base64Data.getBytes(CHARSET_UTF8));
        return new String(descrypt(data, key), CHARSET_UTF8);
    }

    /**
     * 通过base64密钥AES加密后，然后Base64编码成字符串返回
     * @param data 要加密的明文字符串
     * @param base64Key base64密钥
     * @return
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     * @throws java.security.InvalidKeyException
     * @throws javax.crypto.IllegalBlockSizeException
     * @throws javax.crypto.BadPaddingException
     * @throws java.io.UnsupportedEncodingException
     */
    public static String encrypt(String data, String base64Key)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        byte[] key = Base64Coder.decode(base64Key.getBytes(CHARSET_UTF8));
        byte[] enBytes = encrypt(data.getBytes(CHARSET_UTF8), key);
        return new String(UrlBase64.encode(enBytes), CHARSET_UTF8);
    }

	/**
	 * 生成密钥
	 * 
	 * @return
	 * @throws java.security.NoSuchAlgorithmException
	 */
	public static byte[] initKey() throws NoSuchAlgorithmException {
		// 实例化
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		// AES要求密钥长度为128位、192位或256位,192和256报错需要更新/jdk/jre/lib/security里面的2个jar包，替换后就不报错了
		kg.init(256);
		// 生成密钥
		SecretKey secretKey = kg.generateKey();
		// 获取密钥的二进制编码形式
		return secretKey.getEncoded();
	}

}
