/**   
* @Title: Order.java 
* @Package com.wuguan.huate.bean.entity 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午8:40:06 
* @version V1.0   
*/
package com.wuguan.huate.bean.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: Order 
* @Description: 訂單
* @author LiuYanHong
* @date 2020年3月15日 下午8:40:06 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;                             //
	private String orderNo;                         //訂單編號
	private Double money;                           //金額
	private Integer state;                          //狀態 1 成功 0 失敗
	private String payTime;                         //支付時間
	private Integer userId;                         //繳費用戶
	private String desc;                            //描述
	private Integer isClose;                        //是否关闭订单 1 是  0 否
	private String createTime;                        //是否关闭订单 1 是  0 否
	private String houseNo;                        //房号
	private String carNo;                        //车牌号
	private String parkNo;                        //车位号
	private Integer checking;                        //是否对账 1 是 0 否

}
