/**   
* @Title: RoleRes.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午3:17:16 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: RoleRes 
* @Description: 資源角色
* @author LiuYanHong
* @date 2020年3月15日 下午3:17:16 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class RoleRes implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;                             //
	private Integer resId;                          //資源ID
	private Integer roleId;                         //角色ID
}
