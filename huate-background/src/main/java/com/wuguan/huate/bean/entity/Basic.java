/**   
* @Title: Basic.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午7:38:07 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @ClassName: Basic
 * @Description: 物业基本信息
 * @author LiuYanHong
 * @date 2020年4月8日 下午7:38:07
 * 
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Basic implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String company;
	private String workTime;
	private String phone;
	private String address;
	private String uploadPath;
	private String repairsTime;
	private String visitorTime;

}
