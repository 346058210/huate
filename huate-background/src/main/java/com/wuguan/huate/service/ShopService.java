/**   
* @Title: ShopService.java 
* @Package com.wuguan.huate.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月31日 上午1:01:43 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.Shop;
import com.wuguan.huate.bean.vo.ShopVo;

/** 
* @ClassName: ShopService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月31日 上午1:01:43 
*  
*/
public interface ShopService {
	
	List<Shop> getShopsByHouseId(Integer houseId);

	/**
	* @Title: addBatch
	* @Description: 批量新增
	* @param shops
	*/
	void addBatch(List<Shop> shops);

	/**
	* @Title: getListShop
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<ShopVo> getListShop();

}
