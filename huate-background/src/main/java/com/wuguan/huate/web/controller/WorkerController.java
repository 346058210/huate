/**   
* @Title: WorkerController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月1日 下午2:36:53 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.wuguan.huate.annotation.Function;
import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.bean.entity.Resources;
import com.wuguan.huate.bean.entity.Role;
import com.wuguan.huate.bean.entity.Worker;
import com.wuguan.huate.bean.params.WorkerParam;
import com.wuguan.huate.bean.vo.LoginUser;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.ResourcesService;
import com.wuguan.huate.service.RoleService;
import com.wuguan.huate.service.WorkerService;
import com.wuguan.huate.utils.MD5Utils;
import com.wuguan.huate.utils.RedisHelper;
import com.wuguan.huate.web.result.ApiResult;
import com.wuguan.huate.web.result.ResultEnums;

/**
 * @ClassName: WorkerController
 * @Description: 工作人员
 * @author LiuYanHong
 * @date 2020年4月1日 下午2:36:53
 * 
 */
@RestController
public class WorkerController {
	@Autowired
	WorkerService workerService;
	@Autowired
	RedisHelper redisHelper;
	@Autowired
	RoleService roleService;
	@Autowired
	ResourcesService resourcesService;

	@Function(key = "workerAddData")
	@ParamsValidate(validateParams = { @Param(key = "phone", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "password", limit = "0,32", type = ParamType.ENGLISH_NUMBER) })
	@RequestMapping(value = "/worker/addData", method = RequestMethod.POST)
	public ApiResult addData(Worker worker) throws CustomException {
		workerService.addData(worker);
		return ApiResult.success(worker.getId());
	}

	@Function(key = "workerUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/worker/updateData", method = RequestMethod.POST)
	public ApiResult updateData(Worker worker) throws CustomException {
		workerService.updateData(worker);
		return ApiResult.success();
	}

	@Function(key = "workerDelData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/worker/delData", method = RequestMethod.POST)
	public ApiResult delData(Integer id) throws CustomException {
		workerService.delData(id);
		return ApiResult.success();
	}

	@Function(key = "workerUseData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "isUse", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/worker/useData", method = RequestMethod.POST)
	public ApiResult useData(Integer id, Integer isUse) throws CustomException {
		workerService.useData(id, isUse);
		return ApiResult.success();
	}

	@Function(key = "workerDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/worker/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(workerService.detailData(id));
	}

	@Function(key = "workerPageData")
	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/worker/pageData", method = RequestMethod.GET)
	public ApiResult pageData(WorkerParam param) throws CustomException {
		return ApiResult.success(workerService.pageData(param));
	}

	@ParamsValidate(validateParams = { @Param(key = "phone", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "password", limit = "0,32", type = ParamType.ENGLISH_NUMBER) })
	@RequestMapping(value = "/worker/login", method = RequestMethod.POST)
	public ApiResult login(String phone, String password) throws CustomException {
		Worker worker = workerService.getWorkerByPhone(phone);
		if (worker == null) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此账号不存在");
		}
		if (!MD5Utils.encodePassword(password, MD5Utils.SALT).equals(worker.getPassword())) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "密码不正确");
		}
		// 登录成功，返回角色资源
		// 获取当前角色
		List<Role> roles = roleService.getRolesByWorkerId(worker.getId());
		List<Resources> resources = resourcesService.getResourcesByWorkerId(worker.getId());
		// 注销上次的token
		if (worker.getTokenPc() != null && redisHelper.existsKey(worker.getTokenPc())) {
			redisHelper.expire(worker.getTokenPc(), 0);
		}
		String token = UUID.randomUUID().toString();
		LoginUser loginUser = new LoginUser();
		BeanUtils.copyProperties(worker, loginUser);
		loginUser.setRoles(roles);
		loginUser.setResources(resources);
		loginUser.setToken(token);
		Worker work = new Worker();
		work.setId(worker.getId());
		work.setTokenPc(token);
		workerService.updateToken(work);
		redisHelper.set(token, JSONObject.toJSONString(loginUser), 60 * 60 * 24);
		return ApiResult.success(loginUser);
	}
	
	/**
	 * 
	* @Title: offLineByWorkerId
	* @Description: 强制下线
	* @param workerId
	* @return
	* @throws CustomException
	 */
	@Function(key = "workerOffLineByWorkerId")
	@ParamsValidate(validateParams = { @Param(key = "workerId", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/worker/offLineByWorkerId", method = RequestMethod.POST)
	public ApiResult offLineByWorkerId(Integer workerId)throws CustomException{
		workerService.offLineByWorkerId(workerId);
		return ApiResult.success();
		
	}
}
