/**   
* @Title: FeeNormController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月19日 下午10:28:02 
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
import com.wuguan.huate.bean.entity.FeeNorm;
import com.wuguan.huate.bean.params.FeeNormPageParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.FeeNormService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: FeeNormController
 * @Description: 收费标准接口
 * @author LiuYanHong
 * @date 2020年3月19日 下午10:28:02
 * 
 */
@RestController
public class FeeNormController {
	@Autowired
	FeeNormService feeNormService;

	@Function(key = "feeNormAddData")
	@ParamsValidate(validateParams = { @Param(key = "typeName", type = ParamType.CUSTOM),
			@Param(key = "pid", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "price", type = ParamType.CUSTOM), 
			@Param(key = "unit", type = ParamType.CUSTOM),
			@Param(key = "weight", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "mtc", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/feeNorm/addData", method = RequestMethod.POST)
	public ApiResult addData(FeeNorm norm) throws CustomException {
		feeNormService.addData(norm);
		return ApiResult.success(norm.getId());
	}

	@Function(key = "feeNormUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/feeNorm/updateData", method = RequestMethod.POST)
	public ApiResult updateData(FeeNorm norm) throws CustomException {
		feeNormService.updateData(norm);
		return ApiResult.success();
	}

	@Function(key = "feeNormDelData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/feeNorm/delData", method = RequestMethod.POST)
	public ApiResult delData(Integer id) throws CustomException {
		feeNormService.del(id);
		return ApiResult.success();
	}

	@Function(key = "feeNormDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/feeNorm/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(feeNormService.detailData(id));
	}

	@Function(key = "feeNormPageData")
	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/feeNorm/pageData", method = RequestMethod.GET)
	public ApiResult pageData(FeeNormPageParam param) throws CustomException {
		return ApiResult.success(feeNormService.pageData(param));
	}

	@Function(key = "feeNormQueryFeeNorms")
	@RequestMapping(value = "/feeNorm/queryFeeNorms", method = RequestMethod.GET)
	public ApiResult queryFeeNorms() throws CustomException {
		return ApiResult.success(feeNormService.getFeeNorms());
	}
}
