/**   
* @Title: ResourceController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月1日 下午6:57:33 
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
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.ResourcesService;
import com.wuguan.huate.service.RoleResService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: ResourceController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月1日 下午6:57:33
 * 
 */
@RestController
public class ResourceController {
	@Autowired
	ResourcesService resourcesService;
	@Autowired
	RoleResService roleResService;

	@Function(key = "resourceListResources")
	@RequestMapping(value = "/resource/listResources", method = RequestMethod.GET)
	public ApiResult listResources() throws CustomException {
		return ApiResult.success(resourcesService.getResourcesTree());
	}

	@Function(key = "resourceResources")
	@RequestMapping(value = "/resource/resources", method = RequestMethod.GET)
	public ApiResult resources() throws CustomException {
		return ApiResult.success(resourcesService.resources());
	}

	@Function(key = "resourceResourcesByRoleId")
	@ParamsValidate(validateParams = { @Param(key = "roleId", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/resource/resourcesByRoleId", method = RequestMethod.GET)
	public ApiResult resourcesByRoleId(Integer roleId) throws CustomException {
		return ApiResult.success(resourcesService.resourcesByRoleId(roleId));
	}

	@Function(key = "resourceRoleResource")
	@ParamsValidate(validateParams = { @Param(key = "roleId", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "resIds", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/resource/roleResource", method = RequestMethod.POST)
	public ApiResult roleResource(Integer roleId, String resIds) throws CustomException {
		roleResService.addBatch(roleId, resIds);
		return ApiResult.success();
	}
}
