/**   
* @Title: RandomCode.java 
* @Package com.wuguan.huate.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月15日 上午12:46:06 
* @version V1.0   
*/
package com.wuguan.huate.utils;

import java.util.Random;

/**
 * @ClassName: RandomCode
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月15日 上午12:46:06
 * 
 */
public class RandomCode {

	private static String codes = "23456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";

	/**
	 * 随机生成字符
	 * 
	 * @return
	 */
	public static String randomString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			int index = new Random().nextInt(codes.length());
			sb.append(codes.charAt(index));
		}
		return sb.toString();
	}

	public static void main(String[] args) {
		String code = randomString();
		System.err.println(code);
	}
}
