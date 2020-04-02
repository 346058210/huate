/**   
* @Title: ShopVo.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月1日 上午12:43:27 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: ShopVo 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月1日 上午12:43:27 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ShopVo implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String shopNo;
	private Double shopArea;
	private String shopName;
	private String address;
	private Integer sale;
	private Integer electicTypeId;                   //电费收费标准
	private Integer rubbishTypeId;                   //生活垃圾费标准
	private Integer propertyTypeId;                  //物管收费标准
	private Integer waterTypeId;     
	private String dueTime; 
	private String remarkName; 
	
	
}
