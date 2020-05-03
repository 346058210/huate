/**   
* @Title: HouseServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:21:21 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wuguan.huate.bean.entity.House;
import com.wuguan.huate.bean.entity.Resident;
import com.wuguan.huate.bean.entity.Shop;
import com.wuguan.huate.bean.params.HousePageParam;
import com.wuguan.huate.bean.vo.HouseM;
import com.wuguan.huate.bean.vo.HouseVo;
import com.wuguan.huate.bean.vo.UserVo;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.PageInfo;
import com.wuguan.huate.comm.SpringUtil;
import com.wuguan.huate.comm.ThreadLocalManager;
import com.wuguan.huate.db.HouseMapper;
import com.wuguan.huate.db.UserHouseMapper;
import com.wuguan.huate.service.HouseService;
import com.wuguan.huate.service.ParkService;
import com.wuguan.huate.service.ResidentService;
import com.wuguan.huate.service.ShopService;
import com.wuguan.huate.web.result.ResultEnums;

/**
 * @ClassName: HouseServiceImpl
 * @Description: 房屋信息
 * @author LiuYanHong
 * @date 2020年3月15日 下午10:21:21
 * 
 */
@Service
@Transactional
public class HouseServiceImpl implements HouseService {
	@Autowired
	HouseMapper houseMapper;
	@Autowired
	UserHouseMapper userHouseMapper;
	@Autowired
	ResidentService residentService;
	@Autowired
	ShopService shopService;
	@Autowired
	ParkService parkService;

	/**
	 * 批量新增（導入Excel報表）
	 */
	@Override
	public void addBatch(List<House> houses) throws CustomException {
		houseMapper.addBatch(houses);
	}

	/**
	 * 导出报表
	 */
	@Override
	public List<House> getListHouse(HousePageParam param) {
		return houseMapper.getListHouse(param);
	}

	/**
	 * 所有房屋信息
	 */
	@Override
	public List<House> getHouses() {
		return houseMapper.getHouses();
	}

	@Override
	public List<Integer> getBuildings() {
		return houseMapper.getBuildings();
	}

	@Override
	public List<Integer> getUnitsByBuilding(Integer building) {
		return houseMapper.getUnitsByBuilding(building);
	}

	@Override
	public List<Integer> getRoomsByUnit(Integer unit) {
		return houseMapper.getRoomsByUnit(unit);
	}

	/**
	 * 获取绑定房屋信息
	 */
	@Override
	public List<House> getBindHouses(String openid) {
		return houseMapper.getHousesByOpenid(openid);
	}

	@Override
	public House getHouseByHouseNo(String houseNo) {
		return houseMapper.getHouseByHouseNo(houseNo);
	}

	@Override
	public void updateDueTime(String dueTime, Integer relationId) {
		houseMapper.updateDueTime(dueTime, relationId);
	}

	@Override
	public PageInfo<House> pageData(HousePageParam param) {
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<House> page = houseMapper.pageData(param);
		return new PageInfo<House>(page.getTotal(), page);
	}

	@Override
	public PageInfo<House> shopPageData(HousePageParam param) {
		PageHelper.startPage(param.getPage(), param.getRows());
		Page<House> page = houseMapper.shopPageData(param);
		return new PageInfo<House>(page.getTotal(), page);
	}

	@Override
	public HouseVo detailData(Integer id) {
		HouseVo vo = new HouseVo();
		House house = houseMapper.detailData(id);
		BeanUtils.copyProperties(house, vo);
		List<Resident> list = residentService.getResidentsByHouseNo(house.getHouseNo());
		vo.setResidents(list);
		List<Shop> shops = shopService.getShopsByHouseId(vo.getId());
		vo.setShops(shops);
		return vo;
	}

	@Override
	public void addData(HouseVo param) {
		Boolean exist = isExist(param.getHouseNo(), null);
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此房号已存在，请确认");
		}
		houseMapper.addData(param);
		residentService.delByHouseNo(param.getHouseNo());
		residentService.addBatch(param.getResidents());
	}

	@Override
	public Boolean isExist(String houseNo, Integer id) {
		Integer exist = houseMapper.isExist(houseNo, id);
		if (exist != null) {
			return true;
		}
		return false;
	}

	/**
	 * pc页面调用
	 */
	@Override
	public void updateData(HouseVo param) {
		Boolean exist = isExist(param.getHouseNo(), param.getId());
		if (exist) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此房号已存在，请确认");
		}
		houseMapper.updateData(param);
		residentService.delByHouseNo(param.getHouseNo());
		residentService.addBatch(param.getResidents());
	}

	@Override
	public void delData(Integer id) {
		House detailData = houseMapper.detailData(id);
		Integer exist = userHouseMapper.isExist(id);
		List<Resident> list = residentService.getResidentsByHouseNo(detailData.getHouseNo());
		if (exist != null || list.size() > 0) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此房号被绑定，请解绑后再操作");
		}
		detailData.setIsDel(1);
		houseMapper.updateData(detailData);
	}

	@Override
	public void bindHouse(String houseNo) {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo user = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		House house = getHouseByHouseNo(houseNo);
		Integer one = userHouseMapper.queryOne(user.getId(), house.getId());
		if (one != null) {
			throw new CustomException(ResultEnums.BUSINESS.getCode(), "此房号已绑定");
		}
		userHouseMapper.bindHouse(user.getId(), house.getId());
		try {
			House houseByHouseNo = houseMapper.getHouseByHouseNo(houseNo);
			if (houseByHouseNo.getParkNo() != null && !"".equals(houseByHouseNo.getParkNo())) {
				parkService.bindPark(houseByHouseNo.getParkNo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void unboundHouse(String houseNo) {
		ThreadLocalManager bean = SpringUtil.getBean(ThreadLocalManager.class);
		UserVo user = JSONObject.parseObject(bean.getUser().toString(), UserVo.class);
		House house = getHouseByHouseNo(houseNo);
		userHouseMapper.unboundHouse(user.getId(), house.getId());
	}

	@Override
	public List<House> getListHouseByUserId(Integer id) {
		return houseMapper.getListHouseByUserId(id);
	}

	/**
	 * 查询物业到期的房屋
	 */
	@Override
	public List<HouseM> queryExpireHouse() {
		return houseMapper.queryExpireHouse();
	}

	/**
	 * 
	 */
	@Override
	public Boolean isBind(Integer houseId, Integer userId) {
		Integer isBind = userHouseMapper.queryOne(userId, houseId);
		if (isBind != null) {
			return true;
		}
		return false;
	}

	/**
	 * 查询商铺
	 */
	@Override
	public Object queryShop(String name) {
		return houseMapper.queryShop(name);
	}

	@Override
	public void updateNoticeTime(House house) {
		houseMapper.updateData(house);
	}

}
