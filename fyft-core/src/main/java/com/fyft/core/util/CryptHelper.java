package com.fyft.core.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.digest.DigestUtils;


public class CryptHelper {

	private static CryptHelper _inst;
	public final static String DES = "DES";
	public final static String DESEDE = "DESede";
	public final static String MD5 = "MD5";
	
	
	private CryptHelper(){};
	public static CryptHelper getInstance(){
		if(_inst == null)_inst = new CryptHelper();
		return _inst;
	}
	

	public String encryptDES(String str,String securityKey){
		return encryptDES(str,securityKey,DES);
	}
	
	public String encryptDES(String str,String securityKey,String alg){
		try {
			DESKeySpec des = new DESKeySpec(securityKey.getBytes());
			SecretKeyFactory factory = SecretKeyFactory.getInstance(alg);
			SecretKey sk = factory.generateSecret(des);
			Cipher cipher = Cipher.getInstance(alg);
			cipher.init(Cipher.ENCRYPT_MODE, sk);
			byte[] rs = cipher.doFinal(str.getBytes());
			return byte2hex(rs);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return str;
	}
	public String decryptDES(String str,String securityKey){
		return decryptDES(str,securityKey,DES,null);
	}
	public String decryptDES(String str,String securityKey,String alg,String iv){
		try {
			DESKeySpec dks = new DESKeySpec(securityKey.getBytes());
			 SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(alg);  
		     SecretKey sk = keyFactory.generateSecret(dks);
		     Cipher cipher = Cipher.getInstance(alg);
		     AlgorithmParameterSpec ivSpec = null;
		     if(iv!=null){
		    	 ivSpec = new IvParameterSpec(iv.getBytes());
		     }
		     
		     cipher.init(Cipher.DECRYPT_MODE, sk,ivSpec);
		     return new String(cipher.doFinal(hex2byte(str.getBytes())));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public String encryptMD5(String str){
		return DigestUtils.md5Hex(str); 
		
	}
	public String encryptSHA1(String str){
		return DigestUtils.sha1Hex(str);
	}
	
	private static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }
    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs;
    }
	
	
	public static void main(String[] args) {
//		String e = CryptHelper.getInstance().encrypt("vinfo!23",CryptHelper.DES);
//		System.out.println(e);
//		String o = CryptHelper.getInstance().decrypt(e,CryptHelper.DES);
//		System.out.println(o);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("1", "23423");
		map.put("Date", "ssdf");
		
		System.out.println(map.hashCode());
		System.out.println(CryptHelper.getInstance().encryptSHA1("[\"xx\"]"));
		System.out.println(CryptHelper.getInstance().encryptSHA1("111111"));
		
		System.out.println(CryptHelper.getInstance().encryptMD5("abc123"));
		System.out.println(CryptHelper.getInstance().encryptDES("25a1c12b888a40c4b327805f9fb2bd6a@1455961232260@qq","LC_CONFIG_KEY"));
		System.out.println(CryptHelper.getInstance().decryptDES("5ce30d0b1808d0ba2d355b5d88ef374047195a7acfbc905721c280025c5ce79e50f8819a8fcddf2681c7aa469f8128fd55adaef81f86dd4d", "LC_CONFIG_KEY"));
		
		System.out.println(CryptHelper.getInstance().decryptDES("71069dfc8f3f546430c098bb84def959284a6488c7ef7a5b", "bireport",CryptHelper.DESEDE,"initkeys"));
	}
}
