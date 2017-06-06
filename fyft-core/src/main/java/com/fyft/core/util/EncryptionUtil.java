package com.fyft.core.util;

import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptionUtil {
	
	private static final String KEY = "awcTYjjNZ5s=";
	
	/**
	 * des 加密
	 * @param source 原文
	 * @return
	 * @throws Exception
	 */
	public static String desEncryption(String source) throws Exception {
		return desEncrypt(source, KEY);
	}
	
	/**
	 * des 解密
	 * @param source 密文
	 * @return
	 * @throws Exception
	 */
	public static String desDecryption(String source) throws Exception {
		return desDecrypt(source, KEY);
	}
	
	/**
	 * 获取 des 加密的秘钥
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	private static String getDesKey() throws Exception {
		KeyGenerator keyGen = KeyGenerator.getInstance("des");
		SecretKey key = keyGen.generateKey();
		
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(key.getEncoded());
	}
	
	/**
	 * des 加密
	 * @param source 需加密的字符串
	 * @param keyStr 秘钥
	 * @return
	 * @throws Exception
	 */
	private static String desEncrypt(String source, String keyStr) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] keyBytes = decoder.decodeBuffer(keyStr);
		SecretKeySpec key = new SecretKeySpec(keyBytes, "des");
		
		Cipher des = Cipher.getInstance("des");
		des.init(Cipher.ENCRYPT_MODE, key);
		byte[] target = des.doFinal(source.getBytes("UTF-8"));
		
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(target);
	}
	
	/**
	 * 解密
	 * @param source 需解密的字符串
	 * @param keyStr 秘钥
	 * @return
	 * @throws Exception
	 */
	private static String desDecrypt(String source, String keyStr) throws Exception {
		BASE64Decoder decoder = new BASE64Decoder();
		byte[] keyBytes = decoder.decodeBuffer(keyStr);
		SecretKeySpec key = new SecretKeySpec(keyBytes, "des");
		
		byte[] sourceByte = decoder.decodeBuffer(source);
		
		Cipher des = Cipher.getInstance("des");
		des.init(Cipher.DECRYPT_MODE, key);
		byte[] target = des.doFinal(sourceByte);
		return new String(target, "UTF-8");
	}
	
	/**
	 * 生成token
	 * 
	 * @param userCode
	 * @param password
	 * @return
	 */
	public static String createToken(String type, String phone, String password, String userId) throws Exception {
		String tokenSource = type + ";" + phone + ";" + password + ";" + userId + ";" + new Date().getTime();
		return EncryptionUtil.desEncryption(tokenSource);
	}
	
}
