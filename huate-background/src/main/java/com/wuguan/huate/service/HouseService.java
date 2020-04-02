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
import com.wuguan.huate.bean.params.HousePageParam;
import com.wuguan.huate.bean.vo.HouseM;
import com.wuguan.huate.bean.vo.HouseVo;
import com.wuguan.huate.comm.CustomException;
import com.wuguan.huate.comm.PageInfo;

/**
 * @ClassName: HouseService
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月15日 下午10:14:44
 * 
 */
public interface HouseService {

	void addBatch(List<House> param) throws CustomException;

	/**
	 * @Title: getListHouse
	 * @Description: 导出数据
	 * @return
	 */
	List<House> getListHouse(HousePageParam param);

	/**
	 * 
	 * @Title: getHouses
	 * @Description: 获取所有房屋信息
	 * @return
	 */
	List<House> getHouses();

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

	/**
	 * 
	 * @Title: getRoomsByUnit
	 * @Description: 查詢相應的房號
	 * @param unit
	 * @return
	 */
	List<Integer> getRoomsByUnit(Integer unit);

	/**
	 * 
	 * @Title: getBindHouses
	 * @Description: 获取绑定房屋信息
	 * @param openid
	 * @return
	 */
	List<House> getBindHouses(String openid);

	House getHouseByHouseNo(String houseNo);

	/**
	 * @Title: updateDueTime
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param dueTime
	 * @param relationId
	 */
	void updateDueTime(String dueTime, Integer relationId);

	/**
	 * @Title: pageData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param param
	 * @return
	 */
	PageInfo<House> pageData(HousePageParam param);
	
	PageInfo<House> shopPageData(HousePageParam param);

	/**
	 * @Title: detailData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	HouseVo detailData(Integer id);

	/**
	 * @Title: addData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param param
	 */
	void addData(HouseVo param);

	Boolean isExist(String houseNo, Integer id);

	/**
	 * @Title: updateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param param
	 */
	void updateData(HouseVo param);
	
	void updateNoticeTime(House house);

	/**
	* @Title: delData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	*/
	void delData(Integer id);

	/**
	* @Title: bindHouse
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param houseNo
	*/
	void bindHouse(String houseNo);

	/**
	* @Title: unboundHouse
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param houseNo
	*/
	void unboundHouse(String houseNo);

	/**
	* @Title: getListHouseByUserId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	List<House> getListHouseByUserId(Integer id);

	/**
	* @Title: queryExpireHouse
	* @Description: 查询物业也到期的房子
	* @return
	*/
	List<HouseM> queryExpireHouse();

	/**
	* @Title: isBind
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @param id2
	* @return
	*/
	Boolean isBind(Integer houseId, Integer userId);

	/**
	* @Title: queryShop
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param name
	* @return
	*/
	Object queryShop(String name);
}
