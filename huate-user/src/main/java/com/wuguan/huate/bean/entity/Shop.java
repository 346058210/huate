/**   
* @Title: Shop.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月31日 上午12:57:18 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: Shop 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月31日 上午12:57:18 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Shop implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String shopNo;
	private Double shopArea;
	private Integer houseId;
	private Integer isDel;
}
