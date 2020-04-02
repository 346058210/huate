/**   
* @Title: OrderItemMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月22日 下午5:14:15 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuguan.huate.bean.entity.OrderItem;

/** 
* @ClassName: OrderItemMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月22日 下午5:14:15 
*  
*/
public interface OrderItemMapper {
	
	void addBatch(@Param("details")List<OrderItem> details);

	/**
	* @Title: getOrderItemByOrderNo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param orderNo
	* @return
	*/
	List<OrderItem> getOrderItemByOrderNo(@Param("orderNo")String orderNo);

}
