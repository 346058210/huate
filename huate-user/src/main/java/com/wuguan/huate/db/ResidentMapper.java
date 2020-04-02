/**   
* @Title: ResidentMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月20日 下午3:36:50 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuguan.huate.bean.entity.Resident;

/** 
* @ClassName: ResidentMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月20日 下午3:36:50 
*  
*/
public interface ResidentMapper {
	void addData(Resident resident);
	void updateData(Resident resident);
	void delData(@Param("id")Integer id);
	Integer isExist(@Param("idcard")String idcard,@Param("id")Integer id);
	void addBatch(@Param("residents")List<Resident> residents);
	List<Resident> getResidents(@Param("startRow")Integer startRow,@Param("pageSize")Integer pageSize);
	/**
	* @Title: getResidentsByHouseNo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param houseNo
	* @return
	*/
	List<Resident> getResidentsByHouseNo(@Param("houseNo")String houseNo);
	/**
	* @Title: delByHouseNo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param houseNo
	*/
	void delByHouseNo(@Param("houseNo")String houseNo);

}
