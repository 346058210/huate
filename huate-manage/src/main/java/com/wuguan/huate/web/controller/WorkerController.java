/**   
* @Title: WorkerController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月15日 下午5:27:37 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wuguan.huate.bean.entity.Role;
import com.wuguan.huate.bean.entity.Worker;
import com.wuguan.huate.bean.params.WorkerParam;
import com.wuguan.huate.comm.BaseService;
import com.wuguan.huate.comm.Constant;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.service.RoleService;
import com.wuguan.huate.service.WorkerService;
import com.wuguan.huate.utils.MD5Utils;
import com.wuguan.huate.utils.RedisHelper;
import com.wuguan.huate.utils.WechatDecryptDataUtil;
import com.wuguan.huate.web.result.ApiResult;
import com.wuguan.huate.web.result.ResultEnums;

/**
 * @ClassName: WorkerController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月15日 下午5:27:37
 * 
 */
@RestController
public class WorkerController {

	@Autowired
	WorkerService workerService;
	@Autowired
	BaseService baseService;
	@Autowired
	RoleService roleService;

	@Autowired
	RedisHelper redisHelper;

	@RequestMapping(value = "/worker/registerLogin", method = RequestMethod.POST)
	public ApiResult registerLogin(WorkerParam worker) {
		Map<String, Object> map = null;
		// 密码登录
		if (worker.getPhone() != null && worker.getPassword() != null) {
			Worker work = workerService.getByPhone(worker.getPhone());
			if (work == null) {
				throw new CustomException(ResultEnums.BUSINESS.getCode(), "此用户不存在");
			}
			if (!MD5Utils.isPasswordInvalid(work.getPassword(), worker.getPassword(), MD5Utils.SALT)) {
				throw new CustomException(ResultEnums.BUSINESS.getCode(), "密码不正确");
			}
			if (work.getTokenMobile() != null && redisHelper.existsKey(work.getTokenMobile())) {
				redisHelper.expire(work.getTokenMobile(), 0);
			}
			if (work.getOpenid() == null || "".equals(work.getOpenid())) {
				String openId = workerService.getOpendId(worker);
				work.setOpenid(openId);
			}
			String token = UUID.randomUUID().toString();
			work.setTokenMobile(token);
			work.setPassword(null);
			Role role = roleService.getRoleByworkerId(work.getId());
			work.setRole(role);
			Worker wk = new Worker();
			wk.setId(work.getId());
			wk.setOpenid(work.getOpenid());
			wk.setTokenMobile(token);
			workerService.updateData(wk);
			map = new HashedMap<String, Object>();
			redisHelper.set(token, JSON.toJSONString(work), 60 * 60 * 24);
			map.put("worker", work);
			map.put("openid", work.getOpenid());
			map.put("token", token);
		} else if (worker.getEncryptedData() != null && worker.getIv() != null) {// 用户密码
			String result = baseService.getOpenid(worker.getCode(), Constant.JY_APPID, Constant.JY_SECRET);
			JSONObject object = JSONObject.parseObject(result);
			String session_key = object.get("session_key").toString();
			String decryptData = WechatDecryptDataUtil.decryptData(worker.getEncryptedData(), session_key,
					worker.getIv());
			JSONObject data = JSONObject.parseObject(decryptData);
			String phone = data.getString("phoneNumber");
			Worker work = workerService.getByPhone(phone);
			if (work == null) {
				throw new CustomException(ResultEnums.BUSINESS.getCode(), "此用户不存在");
			}
			if (work.getTokenMobile() != null && redisHelper.existsKey(work.getTokenMobile())) {
				redisHelper.expire(work.getTokenMobile(), 0);
			}
			if (work.getOpenid() == null || "".equals(work.getOpenid())) {
				String openId = workerService.getOpendId(worker);
				work.setOpenid(openId);
			}
			String token = UUID.randomUUID().toString();
			work.setTokenMobile(token);
			work.setPassword(null);
			Role role = roleService.getRoleByworkerId(work.getId());
			work.setRole(role);
			Worker wk = new Worker();
			wk.setId(work.getId());
			wk.setOpenid(work.getOpenid());
			wk.setTokenMobile(token);
			workerService.updateData(wk);
			map = new HashedMap<String, Object>();
			redisHelper.set(token, JSON.toJSONString(work), 60 * 60 * 24);
			map.put("worker", work);
			map.put("openid", work.getOpenid());
			map.put("token", token);
		} else {//
			String openId = workerService.getOpendId(worker);
			Worker woker = workerService.getByOpenid(openId);
			if (woker == null) {
				throw new CustomException(ResultEnums.BUSINESS.getCode(), "此用户不存在");
			}
			if (woker.getTokenMobile() != null && redisHelper.existsKey(woker.getTokenMobile())) {
				redisHelper.expire(woker.getTokenMobile(), 0);
			}
			String token = UUID.randomUUID().toString();
			woker.setTokenMobile(token);
			woker.setPassword(null);
			Role role = roleService.getRoleByworkerId(woker.getId());
			woker.setRole(role);
			Worker wk = new Worker();
			wk.setId(woker.getId());
			wk.setOpenid(woker.getOpenid());
			wk.setTokenMobile(token);
			workerService.updateData(wk);
			map = new HashedMap<String, Object>();
			redisHelper.set(token, JSON.toJSONString(woker), 60 * 60 * 24);
			map.put("worker", woker);
			map.put("openid", woker.getOpenid());
			map.put("token", token);
		}
		return ApiResult.success(map);
	}

}
