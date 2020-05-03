/**   
* @Title: RepairsService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月17日 上午12:56:46 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.Repairs;
import com.wuguan.huate.bean.params.RepairsPageParam;
import com.wuguan.huate.bean.vo.RepairsVo;
import com.wuguan.huate.comm.PageInfo;

/** 
* @ClassName: RepairsService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月17日 上午12:56:46 
*  
*/
public interface RepairsService {

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param repairs
	*/
	void addData(Repairs repairs);

	/**
	* @Title: allotHandle
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param repairs
	*/
	void allotHandle(Repairs repairs);

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	RepairsVo detailData(Integer id);

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	PageInfo<RepairsVo> pageData(RepairsPageParam param);

	/**
	* @Title: pageHandleData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	PageInfo<RepairsVo> pageHandleData(RepairsPageParam param);

	/**
	* @Title: handle
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param repairs
	*/
	void handle(Repairs repairs);

	/**
	* @Title: handleOver
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param repairs
	*/
	void handleOver(Repairs repairs);

	/**
	* @Title: revocation
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param repairs
	*/
	void revocation(Repairs repairs);

	/**
	* @Title: queryDelayData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	*/
	List<Repairs> queryDelayData();

}
