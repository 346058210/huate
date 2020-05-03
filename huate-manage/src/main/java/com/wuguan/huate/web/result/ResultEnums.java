/**   
* @Title: ResultEnums.java 
* @Package com.wuguan.huate.web.result 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月14日 下午4:57:58 
* @version V1.0   
*/
package com.wuguan.huate.web.result;

/** 
* @ClassName: ResultEnums 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月14日 下午4:57:58 
*  
*/
public enum ResultEnums {

	SUCCESS(200,"Success"),
	UNKOWN(400,"Bad Request"),
	UNAUTHORIZED(401,"Unauthorized"),
	ERROR(500,"Internal Server Error"),
	BUSINESS(0,"Business Exception");
	
	private Integer code;
	private String msg;
	
	private ResultEnums(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public static ResultEnums getByCode(Integer code) {
		for (ResultEnums enums : ResultEnums.values()) {
			if (enums.code==code) {
				return enums;
			}
		}
		return null;	
	}

}
