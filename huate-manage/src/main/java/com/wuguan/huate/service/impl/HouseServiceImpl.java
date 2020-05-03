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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wuguan.huate.bean.entity.House;
import com.wuguan.huate.db.HouseMapper;
import com.wuguan.huate.service.HouseService;

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

	@Override
	public List<Integer> getBuildings() {
		return houseMapper.getBuildings();
	}

	@Override
	public List<Integer> getUnitsByBuilding(Integer building) {
		return houseMapper.getUnitsByBuilding(building);
	}

	@Override
	public House getHouseByHouseNo(String houseNo) {
		return houseMapper.getHouseByHouseNo(houseNo);
	}

	/**
	 * 查询商铺
	 */
	@Override
	public Object queryShop(String name) {
		return houseMapper.queryShop(name);
	}

	@Override
	public Object detailData(Integer id) {
		return houseMapper.detailData(id);
	}
}
