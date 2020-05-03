/**   
* @Title: HouseMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月17日 下午4:30:49 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.House;
import com.wuguan.huate.bean.params.HousePageParam;
import com.wuguan.huate.bean.vo.HouseM;
import com.wuguan.huate.bean.vo.UserHouseVo;

/**
 * @ClassName: HouseMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月17日 下午4:30:49
 * 
 */
public interface HouseMapper {

	void addBatch(@Param("houses") List<House> houses);

	/**
	 * @Title: getListHouse
	 * @Description: 导出报表
	 * @return
	 */
	List<House> getListHouse(HousePageParam param);

	List<House> getHouses();

	/**
	 * 
	 * @Title: getHousesByOpenid
	 * @Description: 获取绑定房屋信息
	 * @param openid
	 * @return
	 */
	List<House> getHousesByOpenid(@Param("openid") String openid);

	List<Integer> getBuildings();

	List<Integer> getUnitsByBuilding(@Param("building") Integer building);

	List<Integer> getRoomsByUnit(@Param("unit") Integer unit);

	House getHouseByHouseNo(@Param("houseNo") String houseNo);

	/**
	 * @Title: updateDueTime
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param dueTime
	 * @param relationId
	 */
	void updateDueTime(@Param("dueTime") String dueTime, @Param("id") Integer id);

	/**
	 * @Title: pageData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param param
	 * @return
	 */
	Page<House> pageData(HousePageParam param);
	
	Page<House> shopPageData(HousePageParam param);

	/**
	 * @Title: detailData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 */
	House detailData(@Param("id") Integer id);

	/**
	 * @Title: isExist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param houseNo
	 * @return
	 */
	Integer isExist(@Param("houseNo") String houseNo, @Param("id") Integer id);

	void addData(House house);

	/**
	 * @Title: updateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param param
	 */
	void updateData(House param);

	/**
	* @Title: getListHouseByUserId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	List<House> getListHouseByUserId(@Param("userId")Integer userId);

	/**
	* @Title: queryExpireHouse
	* @Description: 物业到期房屋
	* @return
	*/
	List<HouseM> queryExpireHouse();

	/**
	* @Title: queryShop
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param name
	* @return
	*/
	List<House> queryShop(@Param("name")String name);

	/**
	* @Title: queryBindUser
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param list
	* @return
	*/
	List<UserHouseVo> queryBindUser(@Param("houseNos")List<String> houseNos);

	/**
	* @Title: getdueTimeHouses
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<House> getdueTimeHouses();

	/**
	* @Title: queryShops
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @return
	*/
	List<House> queryShops();

}
