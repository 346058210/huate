/**   
* @Title: Role.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午3:14:23 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: Role 
* @Description: 角色 
* @author LiuYanHong
* @date 2020年3月15日 下午3:14:23 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Role implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;                             //
	private String desc;                            //描述
	private String roleName;                        //角色名稱 系統管理員|用戶|維管人員
	private Integer isDel;                          //是否刪除 1 是 0 否
	
}
