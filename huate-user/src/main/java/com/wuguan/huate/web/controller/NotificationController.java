/**   
* @Title: NotificationController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月30日 上午1:42:43 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.bean.params.PageParams;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.NotificationService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: NotificationController
 * @Description: 通知|公告
 * @author LiuYanHong
 * @date 2020年3月30日 上午1:42:43
 * 
 */
@RestController
public class NotificationController {
	@Autowired
	NotificationService notificationService;

	//@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
	//		@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/notification/queryNotifications", method = RequestMethod.GET)
	public ApiResult queryNotifications(PageParams params) throws CustomException {
		return ApiResult.success(notificationService.queryNotifications(params));
	}

	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/notification/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(notificationService.detailData(id));
	}
	
}
