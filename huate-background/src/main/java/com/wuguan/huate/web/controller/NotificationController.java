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

import com.wuguan.huate.annotation.Function;
import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.bean.entity.Notification;
import com.wuguan.huate.bean.params.NotificationPageParam;
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

	@Function(key = "notificationPublishNotification")
	@ParamsValidate(validateParams = { @Param(key = "publisher", type = ParamType.CUSTOM),
			@Param(key = "content", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/notification/publishNotification", method = RequestMethod.POST)
	public ApiResult publishNotification(Notification notification) throws CustomException {
		notificationService.publishNotification(notification);
		return ApiResult.success(notification.getId());
	}

	@Function(key = "notificationUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/notification/updateData", method = RequestMethod.POST)
	public ApiResult updateData(Notification notification) throws CustomException {
		notificationService.updateData(notification);
		return ApiResult.success();

	}

	@Function(key = "notificationDelData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/notification/delData", method = RequestMethod.POST)
	public ApiResult delData(Integer id) throws CustomException {
		notificationService.delData(id);
		return ApiResult.success();

	}

	@Function(key = "notificationDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/notification/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(notificationService.detailData(id));

	}

	@Function(key = "notificationPageData")
	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/notification/pageData", method = RequestMethod.GET)
	public ApiResult pageData(NotificationPageParam param) throws CustomException {
		return ApiResult.success(notificationService.pageData(param));

	}
}
