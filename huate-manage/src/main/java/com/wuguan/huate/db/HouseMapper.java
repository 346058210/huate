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
import com.wuguan.huate.bean.entity.House;

/**
 * @ClassName: HouseMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月17日 下午4:30:49
 * 
 */
public interface HouseMapper {

	List<Integer> getBuildings();

	List<Integer> getUnitsByBuilding(@Param("building") Integer building);
	House getHouseByHouseNo(@Param("houseNo") String houseNo);
	/**
	* @Title: queryShop
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param name
	* @return
	*/
	List<House> queryShop(@Param("name")String name);

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Object detailData(@Param("id")Integer id);

}
