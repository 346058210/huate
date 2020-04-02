/**   
* @Title: ParkServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:23:16 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.Park;
import com.wuguan.huate.bean.params.ParkPageParam;
import com.wuguan.huate.bean.vo.ParkM;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.db.ParkMapper;
import com.wuguan.huate.db.UserParkMapper;
import com.wuguan.huate.service.ParkService;
import com.wuguan.huate.web.result.ResultEnums;

/**
 * @ClassName: ParkServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月15日 下午10:23:16
 * 
 */
@Service
public class ParkServiceImpl implements ParkService {
	@Autowired
	ParkMapper parkMapper;
	@Autowired
	UserParkMapper userParkMapper;

	@Override
	public void addBatch(List<Park> parks) {
		parkMapper.addBatch(parks);
	}

	@Override
	public List<Park> exportExcel(ParkPageParam param) {
		return parkMapper.getListParks(param);
	}

	/**
	 * 查询绑定车位
	 */
	@Override
	public List<Park> getBindPark(String openid) {
		return parkMapper.getBindPark(openid);
	}

	/**
	 * 车位号查询车位信息
	 */
	@Override
	public Park getParksByParkNo(String parkNo) {
		return parkMapper.getParksByParkNo(parkNo);
	}

	/**
	 * 房号查询车位信息
	 */
	@Override
	public List<Park> getParksByHouseNo(String houseNo) {
		return parkMapper.getParksByHouseNo(houseNo);
	}

	@Override
	public void updateDueTime(String dueTime, Integer relationId) {
		parkMapper.updateDueTime(dueTime, relationId);
	}

	@Override
	public void addData(Park park) {
		Boolean exist = isExist(park.getParkNo(), null);
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(),"此车位已存在");
		}
		parkMapper.addData(park);
	}

	@Override
	public void updateData(Park park) {
		Boolean exist = isExist(park.getParkNo(), park.getId());
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(),"此车位已存在");
		}
		parkMapper.updateData(park);
	}

	@Override
	public void delData(Integer id) {
		Integer exist = userParkMapper.isExist(id);
		if (exist!=null) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(),"此车位已被绑定，请解除绑定后操作");
		}
		parkMapper.delData(id);
	}

	@Override
	public Park detailData(Integer id) {
		return parkMapper.detailData(id);
	}

	@Override
	public PageInfo<Park> pageData(ParkPageParam param) {
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<Park> page= parkMapper.pageData(param);
		return new PageInfo<Park>(page.getTotal(), page);
	}

	@Override
	public Boolean isExist(String parkNo, Integer id) {
		Integer exist=parkMapper.isExist(parkNo, id);
		if (exist!=null) {
			return true;
		}
		return false;
	}

	@Override
	public void unboundPark(String parkNo) {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo user = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		Park park = getParksByParkNo(parkNo);
		userParkMapper.unboundPark(user.getId(),park.getId());
		
	}

	@Override
	public void bindPark(String parkNo) {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo user = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		Park park = getParksByParkNo(parkNo);
		Integer one=userParkMapper.queryOne(user.getId(),park.getId());
		if (one!=null) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(),"此车位号已绑定");
		}
		userParkMapper.bindPark(user.getId(),park.getId());
		
	}

	@Override
	public List<Park> getListParksByUserId(Integer id) {
		return parkMapper.getListParksByUserId(id);
	}

	/**
	 * 车牌查询车位
	 */
	@Override
	public Object queryParkByCarNo(String carNo) {
		return parkMapper.queryParkByCarNo(carNo);
	}

	@Override
	public Boolean isBind(Integer userId, Integer parkId) {
		Integer bind = userParkMapper.queryOne(userId, parkId);
		if (bind!=null) {
			return true;
		}
		return false;
	}

	@Override
	public List<ParkM> queryExpirePark() {
		return parkMapper.queryExpirePark();
	}

	@Override
	public void updateNoticeTime(Park park) {
		parkMapper.updateData(park);
	}

}
