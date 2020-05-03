/**   
* @Title: HandleController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月10日 下午2:51:49 
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
import com.wuguan.huate.service.HandleService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: HandleController
 * @Description: 处理结果
 * @author LiuYanHong
 * @date 2020年4月10日 下午2:51:49
 * 
 */
@RestController
public class HandleController {
	@Autowired
	HandleService handleService;

	/**
	 * 
	 * @Title: queryByRepairsId
	 * @Description: 查询某报修流程
	 * @param repairsId
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "handleQueryByRepairsId")
	@ParamsValidate(validateParams = { @Param(key = "repairsId", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/handle/queryByRepairsId", method = RequestMethod.GET)
	public ApiResult queryByRepairsId(Integer repairsId) throws CustomException {
		return ApiResult.success(handleService.queryByRepairsId(repairsId));
	}

}
