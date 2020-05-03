/**   
* @Title: HouseConsumeService.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:15:01 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.HouseConsume;
import com.wuguan.huate.bean.params.HouseConsumePageParam;
import com.wuguan.huate.comm.PageInfo;

/** 
* @ClassName: HouseConsumeService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:15:01 
*  
*/
public interface HouseConsumeService {
	
	/**
	 * 
	* @Title: addBatch
	* @Description: 導入Excel數據
	* @param Consumes
	 */
	void addBatch(List<HouseConsume> Consumes);
	
	/**
	 * 
	* @Title: exportExcel
	* @Description: 導出報表
	* @param startRow
	* @param pageSize
	* @return
	 */
	List<HouseConsume> exportExcel(HouseConsumePageParam param);
	
	/**
	 * 
	* @Title: getHouseConsumeByHouseNo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param houseNo
	* @return
	 */
	List<HouseConsume> getHouseConsumeByHouseNo(String houseNo,Integer type);

	/**
	* @Title: updatePayTime
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param payTime
	* @param relationId
	*/
	void updatePayTime(String payTime, Integer relationId);

	/**
	* @Title: detailData
	* @Description: 详情
	* @param id
	* @return
	*/
	Object detailData(Integer id);

	/**
	* @Title: updateData
	* @Description: 修改
	* @param consume
	* @return
	*/
	void updateData(HouseConsume consume);

	/**
	* @Title: getListDataUnPay
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param houseNo
	*/
	List<HouseConsume> getListDataUnPay(String houseNo);

	/**
	* @Title: waterEelecticFeeCount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	Object waterEelecticFeeCount();

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param consumePageParam
	* @return
	*/
	PageInfo<HouseConsume> pageData(HouseConsumePageParam consumePageParam);

	/**
	* @Title: querySameMonthData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	*/
	List<HouseConsume> querySameMonthData();
}
