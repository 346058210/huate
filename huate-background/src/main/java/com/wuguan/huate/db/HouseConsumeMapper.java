/**   
* @Title: HouseConsumeMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月18日 下午5:18:00 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.HouseConsume;
import com.wuguan.huate.bean.params.HouseConsumePageParam;

/**
 * @ClassName: HouseConsumeMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月18日 下午5:18:00
 * 
 */
public interface HouseConsumeMapper {

	void addBatch(@Param("consumes") List<HouseConsume> consumes);

	List<HouseConsume> exportExcel(HouseConsumePageParam param);

	List<HouseConsume> getHouseConsumeByHouseNo(@Param("houseNo") String houseNo, @Param("type") Integer type);

	/**
	 * @Title: updatePayTime
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param payTime
	 * @param relationId
	 */
	void updatePayTime(@Param("payTime") String payTime, @Param("id") Integer id);

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	HouseConsume detailData(@Param("id")Integer id);

	/**
	* @Title: updateData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param consume
	*/
	void updateData(HouseConsume consume);

	/**
	* @Title: getListDataUnPay
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param houseNo
	* @return
	*/
	List<HouseConsume> getListDataUnPay(@Param("houseNo")String houseNo);

	/**
	* @Title: waterEelecticFeeCount
	* @Description: 12月水电费统计
	* @return
	*/
	List<Map<String, Object>> waterEelecticFeeCount();

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param consumePageParam
	* @return
	*/
	Page<HouseConsume> pageData(HouseConsumePageParam consumePageParam);

	/**
	* @Title: querySameMonthData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<HouseConsume> querySameMonthData();
}
