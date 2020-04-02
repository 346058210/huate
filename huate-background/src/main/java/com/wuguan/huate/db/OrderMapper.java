/**   
* @Title: OrderMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月22日 下午5:53:11 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.Order;
import com.wuguan.huate.bean.params.OrderPageParam;
import com.wuguan.huate.bean.params.PayRecordPageParam;
import com.wuguan.huate.bean.vo.OrderRecord;

/**
 * @ClassName: OrderMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月22日 下午5:53:11
 * 
 */
public interface OrderMapper {

	void addOrder(Order order);

	void closeOrder(@Param("orderNo") String orderNo, @Param("state") Integer state);

	void updateOrder(@Param("orderNo") String orderNo, @Param("payTime") String payTime);
	
	void updateOrderPayId(@Param("orderId") Integer orderId,@Param("prepayId") String prepayId);

	/**
	 * @Title: checkOrder
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	List<Order> checkOrder();

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	Page<Order> pageData(OrderPageParam param);

	/**
	* @Title: detail
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Order detail(@Param("id")Integer id);

	/**
	* @Title: getOrders
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param content
	* @param state
	* @param startRow
	* @param rows
	* @return
	*/
	List<Order> getOrders(OrderPageParam param);

	/**
	* @Title: payRecords
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	Page<OrderRecord> wyLjPayRecords(PayRecordPageParam param);

	/**
	* @Title: sdPayRecords
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	Page<OrderRecord> sdPayRecords(PayRecordPageParam param);

	/**
	* @Title: carPayRecords
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	Page<OrderRecord> carPayRecords(PayRecordPageParam param);

	/**
	* @Title: detailDataByOrderNo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param orderNo
	* @return
	*/
	Order detailDataByOrderNo(@Param("orderNo")String orderNo);

	/**
	* @Title: pageDataByUser
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @param id
	* @return
	*/
	Page<Order> pageDataByUser(@Param("param")OrderPageParam param, @Param("userId")Integer userId);

}
