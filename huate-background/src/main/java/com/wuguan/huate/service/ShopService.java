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
import com.wuguan.huate.bean.params.PageParams;
import com.wuguan.huate.bean.params.ShopRe;
import com.wuguan.huate.bean.vo.ShopVo;
import com.wuguan.huate.comm.PageInfo;

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

	/**
	* @Title: queryAllStore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<Shop> queryAllStore(String shopNo);

	/**
	* @Title: addStore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param shop
	* @return
	*/
	void addStore(Shop shop);

	/**
	* @Title: updateStore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param shop
	*/
	void updateStore(Shop shop);

	/**
	* @Title: delStore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	*/
	void delStore(Integer id);

	/**
	* @Title: detailStore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Shop detailStore(Integer id);

	/**
	* @Title: pageStore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param params
	* @return
	*/
	PageInfo<Shop> pageStore(PageParams params);
	
	Boolean isExist(String shopNo,Integer id);

	/**
	* @Title: updateData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param re
	* @return
	*/
	void updateData(ShopRe re);

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Object detailData(Integer id);

	/**
	* @Title: delData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	*/
	void delData(Integer id);

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param re
	*/
	void addData(ShopRe re);

}
