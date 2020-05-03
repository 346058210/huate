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
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.wuguan.huate.bean.entity.OrderItem;
import com.wuguan.huate.bean.params.ParkCountParam;
import com.wuguan.huate.bean.params.PropertyParam;

/**
 * @ClassName: OrderItemMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月22日 下午5:14:15
 * 
 */
public interface OrderItemMapper {

	void addBatch(@Param("details") List<OrderItem> details);

	/**
	 * @Title: getOrderItemByOrderNo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param orderNo
	 * @return
	 */
	List<OrderItem> getOrderItemByOrderNo(@Param("orderNo") String orderNo);

	/**
	 * @Title: todayPropertyPayFee
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	Double todayPropertyPayFee();

	/**
	 * @Title: todayTotalPayFee
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	Double todayTotalPayFee();

	/**
	 * @Title: totadyPayFee
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	Map<String, Object> totadyPayFee();

	/**
	 * @Title: parkPayFee
	 * @Description: 查询12个月车位费用
	 * @return
	 */
	List<Map<String, Object>> parkPayFee();

	/**
	 * @Title: feeCountByType
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	Object feeCountByType(@Param("startTime") String startTime, @Param("endTime") String endTime);

	/**
	* @Title: propertyFeeCount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	Object propertyFeeCount(PropertyParam param);

	/**
	* @Title: parkFeeCount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	Object parkFeeCount(ParkCountParam param);

}
