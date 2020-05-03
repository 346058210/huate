/**   
* @Title: VisitorController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午6:55:10 
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
import com.wuguan.huate.bean.params.VisitorPageParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.VisitorService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: VisitorController
 * @Description: 访客记录
 * @author LiuYanHong
 * @date 2020年4月11日 下午6:55:10
 * 
 */
@RestController
public class VisitorController {
	@Autowired
	VisitorService visitorService;

	@Function(key = "visitorDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/visitor/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(visitorService.detailData(id));
	}

	@Function(key = "visitorPageData")
	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/visitor/pageData", method = RequestMethod.GET)
	public ApiResult pageData(VisitorPageParam params) throws CustomException {
		return ApiResult.success(visitorService.pageData(params));
	}

}
