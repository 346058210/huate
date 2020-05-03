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

import com.wuguan.huate.annotation.Function;
import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.bean.entity.Information;
import com.wuguan.huate.bean.params.InformationPageParam;
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

	@Function(key = "informationAddData")
	@ParamsValidate(validateParams = { @Param(key = "title", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/information/addData", method = RequestMethod.POST)
	public ApiResult addData(Information information) throws CustomException {
		informationService.addData(information);
		return ApiResult.success(information.getId());
	}

	@Function(key = "informationUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/information/updateData", method = RequestMethod.POST)
	public ApiResult updateData(Information information) throws CustomException {
		informationService.updateData(information);
		return ApiResult.success();
	}

	@Function(key = "informationDelData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/information/delData", method = RequestMethod.POST)
	public ApiResult delData(Integer id) throws CustomException {
		informationService.delData(id);
		return ApiResult.success();
	}

	@Function(key = "informationDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/information/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(informationService.detailData(id));
	}

	@Function(key = "informationPageData")
	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/information/pageData", method = RequestMethod.GET)
	public ApiResult pageData(InformationPageParam params) throws CustomException {
		return ApiResult.success(informationService.pageData(params));
	}

}
