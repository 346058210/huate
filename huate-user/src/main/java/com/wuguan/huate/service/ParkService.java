/**   
* @Title: ParkService.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:15:52 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.Park;
import com.wuguan.huate.bean.params.ParkPageParam;
import com.wuguan.huate.bean.vo.ParkM;
import com.wuguan.huate.comm.PageInfo;

/** 
* @ClassName: ParkService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:15:52 
*  
*/
public interface ParkService {
	void addBatch(List<Park> park);
	List<Park> exportExcel(ParkPageParam param);
	List<Park> getBindPark(String openid);
	Park getParksByParkNo(String parkNo);
	List<Park> getParksByHouseNo(String houseNo);
	/**
	* @Title: updateDueTime
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param dueTime
	* @param relationId
	*/
	void updateDueTime(String dueTime, Integer relationId);
	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param park
	*/
	void addData(Park park);
	/**
	* @Title: updateData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param park
	*/
	void updateData(Park park);
	/**
	* @Title: delData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	*/
	void delData(Integer id);
	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Park detailData(Integer id);
	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	PageInfo<Park> pageData(ParkPageParam param);
	Boolean isExist(String parkNo,Integer id);
	/**
	* @Title: unboundPark
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param parkNo
	*/
	void unboundPark(String parkNo);
	/**
	* @Title: bindPark
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param parkNo
	*/
	void bindPark(String parkNo);
	/**
	* @Title: getListParksByUserId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	List<Park> getListParksByUserId(Integer id);
	/**
	* @Title: queryParkByCarNo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param carNo
	* @return
	*/
	Object queryParkByCarNo(String carNo);
	/**
	* @Title: isBind
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @param id2
	* @return
	*/
	Boolean isBind(Integer userId, Integer parkId);
	
	List<ParkM> queryExpirePark();
	/**
	* @Title: updateNoticeTime
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param park
	*/
	void updateNoticeTime(Park park);

}
