/**   
* @Title: ResidentService.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:16:18 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;
import com.wuguan.huate.bean.entity.Resident;
import com.wuguan.huate.comm.CustomException;

/** 
* @ClassName: ResidentService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:16:18 
*  
*/
public interface ResidentService {
	
	void addData(Resident resident)throws CustomException;
	void updateData(Resident resident)throws CustomException;
	void delData(Integer id)throws CustomException;
	void addBatch(List<Resident> residents)throws CustomException;
	Boolean isExist(String idcard,Integer id)throws CustomException;
	List<Resident> getResidents(Integer startRow,Integer pageSize)throws CustomException;
	List<Resident> getResidentsByHouseNo(String houseNo)throws CustomException;
	/**
	* @Title: delByHouseNo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param houseNo
	*/
	void delByHouseNo(String houseNo);

}
