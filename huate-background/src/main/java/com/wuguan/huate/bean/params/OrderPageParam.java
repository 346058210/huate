/**   
* @Title: OrderPageParam.java 
* @Package com.wuguan.huate.bean.params 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月24日 下午7:18:55 
* @version V1.0   
*/
package com.wuguan.huate.bean.params;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/** 
* @ClassName: OrderPageParam 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月24日 下午7:18:55 
*  
*/
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderPageParam extends PageParams {
	
	private String content;//房号、车位号、订单号
	private Integer feeType;//1 物业费 2 车位费 3 水费 4 电费 5 生活费 6 车位管理费 7 车位租赁费
	private String state;//支付状态
	private String checking;//是否对账 1是 0否
	private String startTime;
	private String endTime;

}
