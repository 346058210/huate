/**   
* @Title: PayUtil.java 
* @Package com.wuguan.huate.utils 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月23日 上午12:55:55 
* @version V1.0   
*/
package com.wuguan.huate.utils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * @ClassName: PayUtil
 * @Description: 微信支付工具
 * @author LiuYanHong
 * @date 2020年3月23日 上午12:55:55
 * 
 */
public class PayUtil {

	/**
	 * 
	 * @Title: getRandomString
	 * @Description: 获取随机字符串
	 * @param length 指定长度
	 * @return
	 */
	public static String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}

	/**  
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串  
     * @param params 需要排序并参与字符拼接的参数组  
     * @return 拼接后字符串  
     */
	public static String createLinkString(Map<String, String> params) {
		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		String prestr="";
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (i==keys.size()-1) {
				prestr=prestr+key+"="+value;
			}else {
				prestr=prestr+key+"="+value+"&";
			}
			
		}
		return prestr;
	}
	
	/**  
     * 除去数组中的空值和签名参数  
     * @param sArray 签名参数组  
     * @return 去掉空值与签名参数后的新签名参数组  
     */
	public static Map<String, String> paraFilter(Map<String, String> sArray){
		Map<String, String> result = new HashMap<String, String>();
		if (sArray==null||sArray.size()<=0) {
			return result;
		}
		for (String key : sArray.keySet()) {
			String value = sArray.get(key);
			if (value==null||value.equals("")||key.equalsIgnoreCase("sign")||key.equalsIgnoreCase("sign_type")) {
				continue;
			}
			result.put(key, value);
		}
		return result;
	}
	
	/**
	 * 
	* @Title: getContentBytes
	* @Description: 指定格式編碼
	* @param content
	* @param charset
	* @return
	 */
	public static byte[] getContentBytes(String content, String charset) {
		if (charset==null||"".equals(charset)) {
			return content.getBytes();
		}
		try {
			return content.getBytes(charset);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("编码异常");
		}
	}
	
	/**
	 * 
	* @Title: sign
	* @Description: 获取签名字符串
	* @param text 需要签名的字符串
	* @param key 密钥
	* @param charset 编码格式
	* @return
	 */
	public static String getSign(String text, String key, String charset) {
		text=text+"&key="+key;
		System.out.println(text);
		return DigestUtils.md5Hex(getContentBytes(text, charset));
		
	}
	
	/**
	 * 
	* @Title: verify
	* @Description: 校验签名是否正确
	* @param text 需要签名字符串
	* @param sign 签名字符串
	* @param key 密钥
	* @param charset 编码格式
	* @return
	 */
	public static boolean verify(String text, String sign, String key, String charset) {
		text=text+key;
		String hex = DigestUtils.md5Hex(getContentBytes(text, charset));
		if (hex.equals(sign)) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * 
	* @Title: doXMLParse
	* @Description: XML解析
	* @param xml
	* @return
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public static Map<String,Object> doXMLParse(String xml){
		if (xml==null||"".equals(xml)) {
			return null;
		}
		Map<String,Object> map=new HashMap<String,Object>();
		InputStream inputStream = new ByteArrayInputStream(xml.getBytes());
		SAXBuilder builder = new SAXBuilder();
		try {
			Document document = builder.build(inputStream);
			Element element = document.getRootElement();
			List list = element.getChildren();
			Iterator it = list.iterator();
			while (it.hasNext()) {
				Element e = (Element) it.next();
				String k = e.getName();
				String v="";
				List children = e.getChildren();
				if (children.isEmpty()) {
					v=e.getTextNormalize();
				}else {
					v=getChildrenText(children);
				}
				map.put(k, v);
			}
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
		
	}

	/**
	* @Title: getChildrenText
	* @Description: 获取子节点xml
	* @param children
	 * @return 
	*/
	private static String getChildrenText(List children) {
		StringBuffer sb = new StringBuffer();
		if(!children.isEmpty()) {
			Iterator it = children.iterator();
			while(it.hasNext()) {
				Element e = (Element) it.next();
				String name = e.getName();
				String value = e.getTextNormalize();
				List list = e.getChildren();
				sb.append("<" + name + ">");
				if (!list.isEmpty()) {
					sb.append(getChildrenText(list));
				}
				sb.append(value);
				sb.append("</" + name + ">");
			}
		}
		return sb.toString();
	}
}
