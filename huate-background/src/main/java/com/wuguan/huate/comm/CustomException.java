/**   
* @Title: CustomException.java 
* @Package com.wuguan.huate.comm 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月14日 下午4:33:25 
* @version V1.0   
*/
package com.wuguan.huate.comm;

import com.wuguan.huate.web.result.ResultEnums;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: CustomException 
* @Description: 自定義異常類
* @author LiuYanHong
* @date 2020年3月14日 下午4:33:25 
*  
*/
@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Integer code;
	private String msg;
	
	public CustomException(Integer code,String msg) {
		this.code=code;
		this.msg=msg;
	}
	
	public CustomException(ResultEnums enums) {
		this.code=enums.getCode();
		this.msg=enums.getMsg();
	}
}
