/**   
* @Title: FeeCountService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月21日 下午3:59:50 
* @version V1.0   
*/
package com.wuguan.huate.service;
import java.util.List;
import java.util.Map;

import com.wuguan.huate.bean.params.ConsumeOrderDetail;

/** 
* @ClassName: FeeCountService 
* @Description: 费用计算及相关费用信息 
* @author LiuYanHong
* @date 2020年3月21日 下午3:59:50 
*  
*/
public interface FeeCountService {
	
	/**
	 * 
	* @Title: propertyFeeCount
	* @Description: 物業費用計算
	* @param houseNo
	* @return
	 */
	Map<String, Object> propertyFeeCount(String houseNo);
	
	/**
	 * 
	* @Title: 
	* @Description: 水电费计算
	* @param houseNo
	* @return
	 */
	List<ConsumeOrderDetail> waterElectricFeeCount(String houseNo,Integer type);
	
	/**
	 * 
	* @Title: parkFeeCount
	* @Description: 租赁|购买停车费用查询
	* @param houseNo
	* @return
	 */
	Map<String, Object> parkFeeCount(String parkNo);
	
	/**
	 * 
	* @Title: buyParkFeeCount
	* @Description: 购买停车费用查询
	* @param houseNo
	* @return
	 */
	List<Map<String, Object>> buyParkFeeCount(String houseNo);

	/**
	* @Title: waitPayFeeCount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	Object waitPayFeeCount();
}
