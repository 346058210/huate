/**   
* @Title: UserController.java 
* @Package com.wuguan.huate.web.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月17日 下午3:49:00 
* @version V1.0   
*/
package com.wuguan.huate.web.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.excel.poi.ExcelBoot;
import com.excel.poi.function.ExportFunction;
import com.wuguan.huate.annotation.Function;
import com.wuguan.huate.annotation.Param;
import com.wuguan.huate.annotation.ParamType;
import com.wuguan.huate.annotation.ParamsValidate;
import com.wuguan.huate.bean.entity.User;
import com.wuguan.huate.bean.params.UserPagePram;
import com.wuguan.huate.bean.params.UserParam;
import com.wuguan.huate.bean.vo.UserExcel;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.service.UserService;
import com.wuguan.huate.utils.RedisHelper;
import com.wuguan.huate.web.result.ApiResult;

/**
 * @ClassName: UserController
 * @Description: 用戶接口
 * @author LiuYanHong
 * @param <V>
 * @date 2020年3月17日 下午3:49:00
 * 
 */
@RestController
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	RedisHelper redisHelper;

	/**
	 * 
	 * @Title: checkUser
	 * @Description: 註冊、登錄
	 * @param user
	 * @return
	 */
	@ParamsValidate(validateParams = { @Param(key = "code", type = ParamType.CUSTOM) })
	@RequestMapping(value = "/user/checkUser", method = RequestMethod.POST)
	public ApiResult checkUser(UserParam param) {
		String openId = userService.getOpenId(param);
		Boolean exist = userService.isExist(openId);
		if (exist) {
			User user = userService.getByOpenid(openId);
			if (user.getToken() != null && redisHelper.existsKey(user.getToken())) {
				redisHelper.expire(user.getToken(), 0);
			}
		}
		String token = UUID.randomUUID().toString();
		param.setOpenid(openId);
		param.setToken(token);
		UserVo user = userService.operationData(param, exist);
		Map<String, Object> map = new HashedMap<String, Object>();
		redisHelper.set(token, JSON.toJSONString(user), 60 * 60 * 24);
		map.put("user", user);
		map.put("openid", openId);
		map.put("token", token);
		return ApiResult.success(map);
	}

	/**
	 * 
	 * @Title: detail
	 * @Description: 查看详情
	 * @param id
	 * @return
	 */
	@Function(key = "userDetail")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/user/detail", method = RequestMethod.GET)
	public ApiResult detail(Integer id) {
		return ApiResult.success(userService.detail(id));
	}

	/**
	 * 
	 * @Title: pageData
	 * @Description: 分页查询
	 * @param param
	 * @return
	 */
	@Function(key = "userPageData")
	@ParamsValidate(validateParams = { @Param(key = "page", limit = "0,11", type = ParamType.NUMBER),
			@Param(key = "rows", limit = "0,11", type = ParamType.NUMBER) })
	@RequestMapping(value = "/user/pageData", method = RequestMethod.GET)
	public ApiResult pageData(UserPagePram param) {
		return ApiResult.success(userService.pageData(param));
	}

	/**
	 * 
	 * @Title: updateUser
	 * @Description: 修改
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "userUpdateUser")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER)})
	@RequestMapping(value = "/user/updateUser", method = RequestMethod.POST)
	public ApiResult updateUser(User user) throws CustomException {
		userService.update(user);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: updateData
	 * @Description: 修改
	 * @return
	 * @throws CustomException
	 */
	@RequestMapping(value = "/user/updateData", method = RequestMethod.POST)
	public ApiResult updateData(User user) throws CustomException {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo vo = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		user.setId(vo.getId());
		userService.update(user);
		return ApiResult.success();
	}

	/**
	 * 
	 * @Title: delUser
	 * @Description: 删除
	 * @param id
	 * @return
	 * @throws CustomException
	 */
	@Function(key = "userDelUser")
	@ParamsValidate(validateParams = { @Param(key = "id", limit = "0,11", type = ParamType.NUMBER)})
	@RequestMapping(value = "/user/delUser", method = RequestMethod.POST)
	public ApiResult delUser(Integer id) throws CustomException {
		userService.del(id);
		return ApiResult.success();
	}
	
	@Function(key = "userExportExcel")
	@RequestMapping(value = "/user/exportExcel", method = RequestMethod.POST)
	public void exportExcel(HttpServletResponse response) throws CustomException {
		ExcelBoot.ExportBuilder(response, "用户信息表", UserExcel.class).exportResponse(null,
				new ExportFunction<Object, UserVo>() {

					@Override
					public List<UserVo> pageQuery(Object param, int pageNum, int pageSize) {
						return userService.getListUser();
					}

					@Override
					public UserExcel convert(UserVo user) {
						UserExcel excel = new UserExcel();
						BeanUtils.copyProperties(user, excel);
						excel.setIsUse(user.getIsUse() == 1 ? "启用" : "禁用");
						return excel;
					}
				});
		;
	}
}
