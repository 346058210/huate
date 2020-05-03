/**   
* @Title: AppointRecordService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午10:13:31 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.AppointRecord;
import com.wuguan.huate.bean.params.AppointRecordPageParam;
import com.wuguan.huate.bean.vo.AppointRecordVo;
import com.wuguan.huate.comm.PageInfo;

/** 
* @ClassName: AppointRecordService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月9日 下午10:13:31 
*  
*/
public interface AppointRecordService {

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	AppointRecordVo detailData(Integer id);
	
	PageInfo<AppointRecordVo> pageData(AppointRecordPageParam param);

	/**
	* @Title: updateData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param appointRecord
	* @return
	*/
	void updateData(AppointRecord appointRecord);

	/**
	* @Title: queryDelayData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	*/
	List<AppointRecordVo> queryDelayData();

}
