/**   
* @Title: ShopMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月31日 上午1:02:49 
* @version V1.0   
*/
package com.wuguan.huate.db;

import com.github.pagehelper.Page;
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

	List<Shop> getShopsByHouseId(@Param("houseId") Integer houseId);

	/**
	 * @Title: addBatch
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param shops
	 */
	void addBatch(@Param("shops") List<Shop> shops);

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
	List<Shop> queryAllStore(@Param("shopNo") String shopNo);

	/**
	 * @Title: addStore
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param shop
	 */
	void addStore(Shop shop);

	/**
	 * @Title: isExist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param shopNo
	 * @param id
	 * @return
	 */
	Integer isExist(@Param("shopNo") String shopNo, @Param("id") Integer id);

	/**
	 * @Title: pageStore
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @return
	 */
	Page<Shop> pageStore();

	/**
	 * @Title: detailStore
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	Shop detailStore(@Param("id") Integer id);

	/**
	 * @Title: updateStore
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param shop
	 */
	void updateStore(Shop shop);

	/**
	 * @Title: updateBatch
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @param shopIds
	 */
	void updateBatch(@Param("houseId") Integer houseId, @Param("shopIds") List<Integer> shopIds);

	/**
	 * @Title: queryIdsByHouseId
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	List<Integer> queryIdsByHouseId(@Param("houseId") Integer houseId);

	/**
	 * @Title: unBind
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 */
	void unBind(@Param("houseId") Integer houseId);

}
