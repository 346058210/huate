/**   
* @Title: AuditController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午11:09:25 
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
import com.wuguan.huate.bean.entity.Audit;
import com.wuguan.huate.bean.params.AuditPageParam;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.AuditService;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: AuditController
 * @Description: 审核人员
 * @author LiuYanHong
 * @date 2020年4月9日 下午11:09:25
 * 
 */
@RestController
public class AuditController {
	@Autowired
	AuditService auditService;

	@Function(key = "auditAddData")
	@ParamsValidate(validateParams = { @Param(key = "workerId", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/audit/addData", method = RequestMethod.POST)
	public ApiResult addData(Audit audit) throws CustomException {
		auditService.addData(audit);
		return ApiResult.success(audit.getId());
	}

	@Function(key = "auditUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/audit/updateData", method = RequestMethod.POST)
	public ApiResult updateData(Audit audit) throws CustomException {
		auditService.updateData(audit);
		return ApiResult.success();
	}

	@Function(key = "auditDelData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/audit/delData", method = RequestMethod.POST)
	public ApiResult delData(Integer id) throws CustomException {
		auditService.delData(id);
		return ApiResult.success();
	}

	@Function(key = "auditDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/audit/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(auditService.detailData(id));
	}

	@Function(key = "auditQueryListData")
	@RequestMapping(value = "/audit/queryListData", method = RequestMethod.GET)
	public ApiResult queryListData(AuditPageParam params) throws CustomException {
		return ApiResult.success(auditService.queryListData(params));
	}

}
