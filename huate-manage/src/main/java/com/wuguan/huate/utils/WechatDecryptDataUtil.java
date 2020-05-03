/**   
* @Title: WechatDecryptDataUtil.java 
* @Package com.wuguan.huate.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月20日 下午4:58:15 
* @version V1.0   
*/
package com.wuguan.huate.utils;

import java.security.Key;
import java.security.Security;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import com.alibaba.fastjson.JSONObject;
import com.wuguan.huate.comm.BaseService;
import com.wuguan.huate.comm.Constant;

/**
 * @ClassName: WechatDecryptDataUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月20日 下午4:58:15
 * 
 */
public class WechatDecryptDataUtil {

	public static void main(String[] args) {
		
		  BaseService service = new BaseService(); String data =
		  service.getOpenid("001n6DkH0xqS3d2KkrkH06FSkH0n6DkP", Constant.JY_APPID,
		  Constant.JY_SECRET); JSONObject object = JSONObject.parseObject(data); String
		  session_key = object.get("session_key").toString();
		  System.out.println("session_key="+session_key);
		 
		String result = decryptData(
				"ofHRZPAx/KGx7lIrGI4z0N99QFse2U1UNNmnr128FkwuJ1IdaFOOuw1P0hWJXhOz4RNetP7PP5ex1qaEiU2r1sAqP9VFrma7Xc7GXQFTx2LM0EAit2QsiRafyGDYzwpVtqlCg7EciUnyVBUeG6Pee1b+6gMPPTbV1giTluCHm8Y3u/zv1jQsZgDpJjmwQ9XID8TV2bKNyi/21mMmVWdOmA==",
				session_key, "A52RNW++rV2MwUWnpm66/w==");
		System.out.println("result = " + result);
	}

	public static String decryptData(String encryptDataB64, String sessionKeyB64, String ivB64) {
		return new String(
				decryptOfDiyIV(Base64.decode(encryptDataB64), Base64.decode(sessionKeyB64), Base64.decode(ivB64)));
	}

	private static final String KEY_ALGORITHM = "AES";
	private static final String ALGORITHM_STR = "AES/CBC/PKCS7Padding";
	private static Key key;
	private static Cipher cipher;

	private static void init(byte[] keyBytes) {
		// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
		int base = 16;
		if (keyBytes.length % base != 0) {
			int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
			byte[] temp = new byte[groups * base];
			Arrays.fill(temp, (byte) 0);
			System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
			keyBytes = temp;
		}
		// 初始化
		Security.addProvider(new BouncyCastleProvider());
		// 转化成JAVA的密钥格式
		key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
		try {
			// 初始化cipher
			cipher = Cipher.getInstance(ALGORITHM_STR, "BC");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解密方法
	 *
	 * @param encryptedData 要解密的字符串
	 * @param keyBytes      解密密钥
	 * @param ivs           自定义对称解密算法初始向量 iv
	 * @return 解密后的字节数组
	 */
	private static byte[] decryptOfDiyIV(byte[] encryptedData, byte[] keyBytes, byte[] ivs) {
		byte[] encryptedText = null;
		init(keyBytes);
		try {
			cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivs));
			encryptedText = cipher.doFinal(encryptedData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedText;
	}
}
