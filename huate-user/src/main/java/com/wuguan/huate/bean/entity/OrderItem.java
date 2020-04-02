/**   
* @Title: OrderItem.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午8:44:44 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: OrderItem 
* @Description: 訂單項目
* @author LiuYanHong
* @date 2020年3月15日 下午8:44:44 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderItem implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;                             //
	private String name;
	private Integer num;                            //繳費月數
	private Double money;                           //繳費金額
	private Integer relationId;                     //關聯ID（停車表ID、戶號ID、水電消耗表ID）
	private Integer userId;                         //繳費用戶
	private String dueTime;                         //到期時間
	private String desc;                            //描述
	private String orderNo;                         //訂單編號
	private Integer feeTypeId;                      //繳費類型

}
