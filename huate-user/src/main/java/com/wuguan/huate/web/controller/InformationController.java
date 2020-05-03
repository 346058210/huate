/**   
* @Title: InformationController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月10日 下午3:10:42 
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
import com.wuguan.huate.service.InformationService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: InformationController
 * @Description: 资讯
 * @author LiuYanHong
 * @date 2020年4月10日 下午3:10:42
 * 
 */
@RestController
public class InformationController {
	@Autowired
	InformationService informationService;

	//@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
	//		@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/information/queryInformations", method = RequestMethod.GET)
	public ApiResult queryInformations(PageParams params) throws CustomException {
		return ApiResult.success(informationService.queryInformations(params));
	}

	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/information/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(informationService.detailData(id));
	}
}
