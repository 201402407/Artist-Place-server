package com.artiplace.api.comn.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EncUtils {
	
	// �ؽ� �Լ�(SHA-256)
	public static String hashing(String word, String type) {
		try {
			if(type.equals("sha256")) {
				return sha256(word);
			}
			if(type.equals("md5")) {
				return md5(word);
			}
			
			return word;
		}
		catch(NoSuchAlgorithmException e) {
			log.error("hasing Error ::: " + e.getMessage());
			return null;
		}
	}
	
	// MD5 �ؽ�
	private static String md5(String msg) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    md.update(msg.getBytes());
	    return byteToHexString(md.digest());
	}
	
	// SHA-256 �ؽ�
	private static String sha256(String msg) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("SHA-256");
	    md.update(msg.getBytes());
	    return byteToHexString(md.digest());
	}
	
	// byte �迭 -> 16����(HEX) ���ڿ� ��ȯ
	private static String byteToHexString(byte[] data) {
	    StringBuilder sb = new StringBuilder();
	    for(byte b : data) {
	        sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
	    }
	    return sb.toString();
	}
}
