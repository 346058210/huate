/**   
* @Title: UserServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:26:32 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.User;
import com.wuguan.huate.bean.params.UserPagePram;
import com.wuguan.huate.bean.params.UserParam;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.comm.BaseService;
import com.wuguan.huate.comm.Constant;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.db.UserHouseMapper;
import com.wuguan.huate.db.UserMapper;
import com.wuguan.huate.db.UserParkMapper;
import com.wuguan.huate.service.UserService;
import com.wuguan.huate.web.result.ResultEnums;

/** 
* @ClassName: UserServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:26:32 
*  
*/
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserHouseMapper userHouseMapper;
	@Autowired
	UserParkMapper userParkMapper;
	@Autowired
	BaseService baseService;

	@Override
	public void add(User user)throws CustomException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setCreateTime(sdf.format(new Date()));
		userMapper.addData(user);
	}

	@Override
	public void update(User user) throws CustomException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		user.setUpdateTime(sdf.format(new Date()));
		userMapper.updateData(user);
	}
	
	/**
	 * 删除
	 */
	@Override
	public void del(Integer id) throws CustomException{
		Integer use = userHouseMapper.isUse(id);
		Integer use2 = userParkMapper.isUse(id);
		if (use!=0||use2!=0) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(),"此用户存在绑定关系，暂无法删除");
		}
		User user = new User();
		user.setId(id);
		user.setIsDel(1);
		userMapper.updateData(user);
	}
	
	/**
	 * 判断微信识别码是否存在
	 */
	@Override
	public Boolean isExist(String openid) throws CustomException{
		Integer exist=userMapper.isExist(openid);
		if (exist!=0) {
			return true;
		}
		return false;
	}

	@Override
	public String getOpenId(UserParam param) {
		String result = baseService.getOpenid(param.getCode(),Constant.XJ_APPID,Constant.XJ_SECRET);
		JSONObject object = JSONObject.parseObject(result);
		String openid = object.get("openid").toString();
		return openid;
	}
	
	@Override
	public UserVo operationData(UserParam param,Boolean exist) {
		if (!exist) {
			add(param);
		}else {
			update(param);
		}
		User user = userMapper.getByOpenid(param.getOpenid());
		UserVo vo = new UserVo();
		BeanUtils.copyProperties(user, vo);
		return vo;
	}

	@Override
	public User getByOpenid(String openid) {
		return userMapper.getByOpenid(openid);
	}

	@Override
	public UserVo detail(Integer id) {
		User user = userMapper.detail(id);
		UserVo vo = new UserVo();
		BeanUtils.copyProperties(user, vo);
		return vo;
	}

	@Override
	public PageInfo<UserVo> pageData(UserPagePram param) {
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<User> page = userMapper.getUsersByContent(param.getContent());
		List<UserVo> list = new ArrayList<UserVo>();
		if (page.size()>0) {
			for (User user : page) {
				UserVo vo = new UserVo();
				BeanUtils.copyProperties(user, vo);
				list.add(vo);
			}
		}
		return new PageInfo<UserVo>(page.getTotal(), list);
	}

	@Override
	public List<UserVo> getListUser() {
		List<User> users = userMapper.getListUser();
		List<UserVo> list = new ArrayList<UserVo>();
		if (users.size()>0) {
			for (User user : users) {
				UserVo vo = new UserVo();
				BeanUtils.copyProperties(user, vo);
				list.add(vo);
			}
		}
		
		return list;
	}

	@Override
	public User getUserByOrderNo(String orderNo) {
		return userMapper.getUserByOrderNo(orderNo);
	}

	@Override
	public Integer todayRegisterNum() {
		return userMapper.todayRegisterNum();
	}

}
