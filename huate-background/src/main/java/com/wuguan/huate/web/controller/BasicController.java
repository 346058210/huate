/**   
* @Title: BasicController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午7:40:55 
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
import com.wuguan.huate.bean.entity.Basic;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.BasicService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: BasicController
 * @Description: 物业基础信息
 * @author LiuYanHong
 * @date 2020年4月8日 下午7:40:55
 * 
 */
@RestController
public class BasicController {
	@Autowired
	BasicService basicService;

	@Function(key = "basicAddData")
	@ParamsValidate(validateParams = { @Param(key = "company", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/basic/addData", method = RequestMethod.POST)
	public ApiResult addData(Basic basic) throws CustomException {
		basicService.addData(basic);
		return ApiResult.success(basic.getId());
	}

	@Function(key = "basicUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/basic/updateData", method = RequestMethod.POST)
	public ApiResult updateData(Basic basic) throws CustomException {
		basicService.updateData(basic);
		return ApiResult.success();
	}

	@Function(key = "basicDelData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/basic/delData", method = RequestMethod.POST)
	public ApiResult delData(Integer id) throws CustomException {
		basicService.delData(id);
		return ApiResult.success();
	}

	@Function(key = "basicDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/basic/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(basicService.detailData(id));
	}

	@Function(key = "basicQueryData")
	@RequestMapping(value = "/basic/queryData", method = RequestMethod.GET)
	public ApiResult queryData(String company) throws CustomException {
		return ApiResult.success(basicService.queryData(company));
	}

}
