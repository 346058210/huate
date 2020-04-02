/**   
* @Title: ShopServiceImpl.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月31日 上午1:02:21 
* @version V1.0   
*/
package com.wuguan.huate.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wuguan.huate.bean.entity.Shop;
import com.wuguan.huate.bean.vo.ShopVo;
import com.wuguan.huate.db.ShopMapper;
import com.wuguan.huate.service.ShopService;

/** 
* @ClassName: ShopServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月31日 上午1:02:21 
*  
*/
@Service
public class ShopServiceImpl implements ShopService {
	@Autowired
	ShopMapper shopMapper;

	@Override
	public List<Shop> getShopsByHouseId(Integer houseId) {
		return shopMapper.getShopsByHouseId(houseId);
	}

	@Override
	public void addBatch(List<Shop> shops) {
		shopMapper.addBatch(shops);
	}

	@Override
	public List<ShopVo> getListShop() {
		return shopMapper.getListShop();
	}

}
