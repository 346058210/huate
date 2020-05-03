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

import com.wuguan.huate.annotation.Function;
import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.bean.entity.LiveServe;
import com.wuguan.huate.bean.params.LiveServeParam;
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

	@Function(key = "liveServeAddData")
	@ParamsValidate(validateParams = { @Param(key = "name", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/liveServe/addData", method = RequestMethod.POST)
	public ApiResult addData(LiveServe liveServe) throws CustomException {
		liveServeService.addData(liveServe);
		return ApiResult.success(liveServe.getId());
	}

	@Function(key = "liveServeUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/liveServe/updateData", method = RequestMethod.POST)
	public ApiResult updateData(LiveServe liveServe) throws CustomException {
		liveServeService.updateData(liveServe);
		return ApiResult.success();
	}

	@Function(key = "liveServeDelData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/liveServe/delData", method = RequestMethod.POST)
	public ApiResult delData(Integer id) throws CustomException {
		liveServeService.delData(id);
		return ApiResult.success();
	}

	@Function(key = "liveServeDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/liveServe/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(liveServeService.detailData(id));
	}

	@Function(key = "liveServePageData")
	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/liveServe/pageData", method = RequestMethod.GET)
	public ApiResult pageData(LiveServeParam params) throws CustomException {
		return ApiResult.success(liveServeService.pageData(params));
	}
}
