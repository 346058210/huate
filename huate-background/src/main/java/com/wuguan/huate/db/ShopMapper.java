/**   
* @Title: ShopMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月31日 上午1:02:49 
* @version V1.0   
*/
package com.wuguan.huate.db;

import com.wuguan.huate.bean.entity.Shop;
import com.wuguan.huate.bean.vo.ShopVo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

/** 
* @ClassName: ShopMapper 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author LiuYanHong
* @date 2020年3月31日 上午1:02:49 
*  
*/
public interface ShopMapper {
	
	List<Shop> getShopsByHouseId(@Param("houseId")Integer houseId);

	/**
	* @Title: addBatch
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param shops
	*/
	void addBatch(@Param("shops")List<Shop> shops);

	/**
	* @Title: getListShop
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<ShopVo> getListShop();

}
