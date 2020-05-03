/**   
* @Title: HandleMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月10日 下午2:53:45 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuguan.huate.bean.entity.Handle;

/** 
* @ClassName: HandleMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月10日 下午2:53:45 
*  
*/
public interface HandleMapper {

	/**
	* @Title: queryByRepairsId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param repairsId
	* @return
	*/
	List<Handle> queryByRepairsId(@Param("repairsId")Integer repairsId);

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param handle
	*/
	void addData(Handle handle);

}
