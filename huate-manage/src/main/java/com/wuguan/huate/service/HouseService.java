/**   
* @Title: HouseService.java 
* @Package com.wuguan.huate.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月15日 下午10:14:44 
* @version V1.0   
*/
package com.wuguan.huate.service;

import java.util.List;

import com.wuguan.huate.bean.entity.House;

/**
 * @ClassName: HouseService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月15日 下午10:14:44
 * 
 */
public interface HouseService {

	/**
	 * 
	 * @Title: getBuildings
	 * @Description: 獲取樓棟
	 * @return
	 */
	List<Integer> getBuildings();

	/**
	 * 
	 * @Title: getUnitsByBuilding
	 * @Description: 查詢相應的單元
	 * @param building
	 * @return
	 */
	List<Integer> getUnitsByBuilding(Integer building);

	House getHouseByHouseNo(String houseNo);

	/**
	* @Title: queryShop
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param name
	* @return
	*/
	Object queryShop(String name);

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Object detailData(Integer id);
}
