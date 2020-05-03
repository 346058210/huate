/**   
* @Title: RoleController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月1日 下午6:56:24 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wuguan.huate.annotation.Function;
import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.bean.entity.Role;
import com.wuguan.huate.bean.entity.Worker;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.RoleService;
import com.wuguan.huate.service.WorkerRoleService;
import com.wuguan.huate.service.WorkerService;
import com.wuguan.huate.utils.RedisHelper;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: RoleController
 * @Description: 角色
 * @author LiuYanHong
 * @date 2020年4月1日 下午6:56:24
 * 
 */
@RestController
public class RoleController {
	@Autowired
	RoleService roleService;
	@Autowired
	WorkerRoleService workerRoleService;
	@Autowired
	WorkerService workerService;
	@Autowired
	RedisHelper redisHelper;

	@Function(key = "roleAddData")
	@ParamsValidate(validateParams = { @Param(key = "roleName", limit = "0,11", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/role/addData", method = RequestMethod.POST)
	public ApiResult addData(Role role) throws CustomException {
		roleService.add(role);
		return ApiResult.success(role.getId());
	}

	@Function(key = "roleUpdateData")
	@ParamsValidate(validateParams = { @Param(key = "roleName", limit = "0,11", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/role/updateData", method = RequestMethod.POST)
	public ApiResult updateData(Role role) throws CustomException {
		roleService.update(role);
		return ApiResult.success();
	}

	@Function(key = "roleDelData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/role/delData", method = RequestMethod.POST)
	public ApiResult delData(Integer id) throws CustomException {
		roleService.del(id);
		return ApiResult.success();
	}

	@Function(key = "roleDetailData")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/role/detailData", method = RequestMethod.GET)
	public ApiResult detailData(Integer id) throws CustomException {
		return ApiResult.success(roleService.detailData(id));
	}

	@Function(key = "roleListRoles")
	@RequestMapping(value = "/role/listRoles", method = RequestMethod.GET)
	public ApiResult listRoles() throws CustomException {
		return ApiResult.success(roleService.listRoles());
	}

	@Function(key = "roleWorkerRole")
	@ParamsValidate(validateParams = { @Param(key = "workerId", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "roleIds", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/role/workerRole", method = RequestMethod.POST)
	public ApiResult workerRole(Integer workerId, String roleIds) throws CustomException {
		workerRoleService.addBatch(workerId, roleIds);
		return ApiResult.success();
	}
	
	/**
	 * 
	* @Title: offLineByRoleId
	* @Description: 强制下线
	* @param roleId
	* @return
	* @throws CustomException
	 */
	@Function(key = "roleOffLineByRoleId")
	@ParamsValidate(validateParams = { @Param(key = "roleId", limit = "0,11", type = ParamType.NUMBER)})
	@RequestMapping(value = "/role/offLineByRoleId", method = RequestMethod.POST)
	public ApiResult offLineByRoleId(Integer roleId)throws CustomException{
		List<Worker> workers=workerService.queryWorkersByRoleId(roleId);
		if (workers.size()!=0) {
			for (Worker worker : workers) {
				if (worker.getTokenPc()!=null) {
					Boolean existsKey = redisHelper.existsKey(worker.getTokenPc());
					if (existsKey) {
						redisHelper.expire(worker.getTokenPc(), 0);
					}
				}
			}
		}
		return ApiResult.success();
		
	}
}
