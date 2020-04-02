/**   
* @Title: OrderRecord.java 
* @Package com.wuguan.huate.bean.vo 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月26日 下午11:38:09 
* @version V1.0   
*/
package com.wuguan.huate.bean.vo;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: OrderRecord 
* @Description: 缴费记录
* @author LiuYanHong
* @date 2020年3月26日 下午11:38:09 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderRecord implements Serializable{

	private static final long serialVersionUID = 1L;
	private String orderNo;
	private String money;
	private String phone;
	private String desc;
	private Integer isPay;
	private String payTime;
	private String houseNo;                        //房号
	private String carNo;                        //车牌号
	private String parkNo;                        //车位号

}
