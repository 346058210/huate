/**   
* @Title: LiveServeController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午2:44:43 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.LiveServeService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: LiveServeController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月11日 下午2:44:43
 * 
 */
@RestController
public class LiveServeController {
	@Autowired
	LiveServeService liveServeService;


	@RequestMapping(value = "/liveServe/queryLiveServes",method = RequestMethod.GET)
	public ApiResult queryLiveServes()throws CustomException{
		return ApiResult.success(liveServeService.queryLiveServes());	
	}
	
	@RequestMapping(value = "/liveServe/detailLiveServe",method = RequestMethod.GET)
	public ApiResult detailLiveServe(Integer id)throws CustomException{
		return ApiResult.success(liveServeService.detailLiveServe(id));	
	}
}
