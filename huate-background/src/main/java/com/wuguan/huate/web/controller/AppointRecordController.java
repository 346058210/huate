/**   
* @Title: AppointRecordController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午10:09:59 
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
import com.wuguan.huate.bean.entity.AppointRecord;
import com.wuguan.huate.bean.params.AppointRecordPageParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.AppointRecordService;
import com.wuguan.huate.web.result.ApiResult;

/** 
* @ClassName: AppointRecordController 
* @Description: 生活记录
* @author LiuYanHong
* @date 2020年4月9日 下午10:09:59 
*  
*/
@RestController
public class AppointRecordController {
	@Autowired
	AppointRecordService appointRecordService;
	
	@Function(key = "appointRecordUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/appointRecord/updateData",method = RequestMethod.POST)
	public ApiResult updateData(AppointRecord appointRecord)throws CustomException{
		appointRecordService.updateData(appointRecord);
		return ApiResult.success();
	}
	
	@Function(key = "appointRecordDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/appointRecord/detailData",method = RequestMethod.GET)
	public ApiResult detailData(Integer id)throws CustomException{
		return ApiResult.success(appointRecordService.detailData(id));
	}
	
	@Function(key = "appointRecordPageData")
	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/appointRecord/pageData",method = RequestMethod.GET)
	public ApiResult pageData(AppointRecordPageParam param)throws CustomException{
		return ApiResult.success(appointRecordService.pageData(param));
	}

}
