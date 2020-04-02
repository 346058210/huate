package com.wuguan.huate.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * @author: lyl
 * @Description:
 * @date: 2019年11月26日 下午2:03:30
 */
public class MD5Utils {
public static final String SALT="123456";
	
	public static final Md5PasswordEncoder ENCODER = new Md5PasswordEncoder();
	/**
	 * 对原始密码进行加密和加盐
	 * @param password 原始密码
	 * @param salt 盐值
	 * @return
	 */
	public static String encodePassword(String password, String salt) {
		return ENCODER.encodePassword(password, salt);
	}

	/**
	 * 校验密码是否正确
	 * @param encPass 加密的密码
	 * @param rawPass 原始密码
	 * @param salt 盐值
	 * @return
	 */
	public static boolean isPasswordInvalid(String encPass, String rawPass, String salt) {
		return ENCODER.isPasswordValid(encPass, rawPass, salt);
	}

	public static void main(String[] args) throws ParseException {
		String password="123456";
		String encodePassword = encodePassword(password, SALT);
		System.out.println(encodePassword);
	}
}