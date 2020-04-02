/**   
* @Title: OrderItemService.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:15:35 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;
import com.wuguan.huate.bean.entity.OrderItem;
import com.wuguan.huate.comm.CustomException;

/** 
* @ClassName: OrderItemService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:15:35 
*  
*/
public interface OrderItemService {
	
	//訂單明細
	void addBatch(List<OrderItem> detail)throws CustomException;

	/**
	* @Title: getOrderItemByOrderNo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param orderNo
	*/
	List<OrderItem> getOrderItemByOrderNo(String orderNo);

}
