/**   
* @Title: AddressBook.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午10:44:35 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: AddressBook 
* @Description: 通讯录 
* @author LiuYanHong
* @date 2020年4月8日 下午10:44:35 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AddressBook implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String phone;
	private String address;
	private Integer isInner;                 //是否内部 1 是 0 否

}
