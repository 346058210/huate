/**   
* @Title: FeeNormService.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:14:03 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.FeeNorm;
import com.wuguan.huate.bean.params.FeeNormPageParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.PageInfo;

/** 
* @ClassName: FeeNormService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:14:03 
*  
*/
public interface FeeNormService {
	
	void addData(FeeNorm norm)throws CustomException;
	void updateData(FeeNorm norm)throws CustomException;
	void del(Integer id)throws CustomException;
	List<FeeNorm> getFeeNorms()throws CustomException;
	Boolean isExist(String typeName,Integer id)throws CustomException;
	Boolean isUse(Integer id)throws CustomException;
	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	FeeNorm detailData(Integer id);
	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	PageInfo<FeeNorm> pageData(FeeNormPageParam param);
}
