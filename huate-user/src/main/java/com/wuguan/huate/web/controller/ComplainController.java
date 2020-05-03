/**   
* @Title: ComplainController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月10日 上午10:50:17 
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
import com.wuguan.huate.bean.entity.Complain;
import com.wuguan.huate.bean.params.ComplainPageParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.ComplainService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: ComplainController
 * @Description: 投诉建议
 * @author LiuYanHong
 * @date 2020年4月10日 上午10:50:17
 * 
 */
@RestController
public class ComplainController {
	@Autowired
	ComplainService complainService;
	
	@RequestMapping(value = "/complain/addData", method = RequestMethod.POST)
	public ApiResult addData(Complain complain) throws CustomException {
		complainService.addData(complain);
		return ApiResult.success(complain.getId());
	}
	
	/**
	 * 
	* @Title: updateData
	* @Description: 撤销
	* @param complain
	* @return
	* @throws CustomException
	 */
	@RequestMapping(value = "/complain/updateData", method = RequestMethod.POST)
	public ApiResult updateData(Complain complain) throws CustomException {
		complainService.updateData(complain);
		return ApiResult.success();
	}
	
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/complain/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(complainService.detailData(id));
	}

	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/complain/pageData", method = RequestMethod.GET)
	public ApiResult pageData(ComplainPageParam param) throws CustomException {
		return ApiResult.success(complainService.pageData(param));
	}
}
