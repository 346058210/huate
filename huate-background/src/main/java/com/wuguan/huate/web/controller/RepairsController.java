/**   
* @Title: RepairsController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午5:46:53 
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
import com.wuguan.huate.bean.entity.Repairs;
import com.wuguan.huate.bean.params.RepairsPageParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.RepairsService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: RepairsController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月11日 下午5:46:53
 * 
 */
@RestController
public class RepairsController {
	@Autowired
	RepairsService repairsService;

	@Function(key = "repairsUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/repairs/updateData", method = RequestMethod.POST)
	public ApiResult updateData(Repairs repairs) throws CustomException {
		repairsService.updateData(repairs);
		return ApiResult.success();
	}

	@Function(key = "repairsDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/repairs/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(repairsService.detailData(id));
	}

	@Function(key = "repairsPageData")
	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/repairs/pageData", method = RequestMethod.GET)
	public ApiResult pageData(RepairsPageParam params) throws CustomException {
		return ApiResult.success(repairsService.pageData(params));
	}

}
