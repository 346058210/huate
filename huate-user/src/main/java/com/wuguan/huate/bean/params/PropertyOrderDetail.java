/**   
* @Title: PropertyOrderDetail.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月22日 下午8:06:12 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: PropertyOrderDetail 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月22日 下午8:06:12 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PropertyOrderDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer houseId;
	private String houseNo;
	private String name;
	private String timeFrame;
	private Integer money;
	private Integer month;
	private String daoqi_time;
	private Integer type;

}
