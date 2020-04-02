/**   
* @Title: UserHouseMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月24日 下午7:00:57 
* @version V1.0   
*/
package com.wuguan.huate.db;

import org.apache.ibatis.annotations.Param;

/** 
* @ClassName: UserHouseMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月24日 下午7:00:57 
*  
*/
public interface UserHouseMapper {
	
	Integer isUse(@Param("userId")Integer userId);

	/**
	* @Title: isExist
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Integer isExist(@Param("houseId")Integer houseId);

	/**
	* @Title: bindHouse
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @param id2
	*/
	void bindHouse(@Param("userId")Integer userId, @Param("houseId")Integer houseId);

	/**
	* @Title: unboundHouse
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @param id2
	*/
	void unboundHouse(@Param("userId")Integer userId, @Param("houseId")Integer houseId);

	/**
	* @Title: queryOne
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @param id2
	* @return
	*/
	Integer queryOne(@Param("userId")Integer userId, @Param("houseId")Integer houseId);

}
