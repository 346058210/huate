/**   
* @Title: Park.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午9:16:29 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: Park 
* @Description: 停車信息
* @author LiuYanHong
* @date 2020年3月15日 下午9:16:29 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Park implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;                             //
	private String houseNo;                         //戶號
	private String carNo;                           //車牌號
	private String parkNo;                          //車位號
	private String ownerOne;                           //車主1
	private String ownerTwo;                           //車主2
	private String phoneOne;                           //電話1
	private String phoneTwo;                           //電話2
	private Integer type;                           //類型 1 租停 2 自購
	private String dueTime;                         //到期時間
	private Integer normId;							//收费标准
	private Double area;							//面积
	private Integer genre;							//车位类型
	private Integer isDel;							//是否删除 1 是 0 否
	private String noticeTime;							//通知时间
}
