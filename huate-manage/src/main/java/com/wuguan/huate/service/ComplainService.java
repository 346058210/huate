/**   
* @Title: ComplainService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月16日 下午10:13:14 
* @version V1.0   
*/
package com.wuguan.huate.service;
import com.wuguan.huate.bean.entity.Complain;
import com.wuguan.huate.bean.params.ComplainPageParam;
import com.wuguan.huate.bean.vo.ComplainVo;
import com.wuguan.huate.comm.PageInfo;

/** 
* @ClassName: ComplainService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年4月16日 下午10:13:14 
*  
*/
public interface ComplainService {

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	PageInfo<ComplainVo> pageData(ComplainPageParam param);

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	ComplainVo detailData(Integer id);

	/**
	* @Title: allotHandle
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param complain
	* @return
	*/
	void allotHandle(Complain complain);

	/**
	* @Title: handle
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param complain
	*/
	void handle(Complain complain);

	/**
	* @Title: revocation
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param complain
	*/
	void revocation(Complain complain);

	/**
	* @Title: handleOver
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param complain
	*/
	void handleOver(Complain complain);

}
