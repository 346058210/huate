/**   
* @Title: ParkMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月20日 上午10:46:48 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.Park;
import com.wuguan.huate.bean.params.ParkPageParam;
import com.wuguan.huate.bean.vo.ParkM;

/**
 * @ClassName: ParkMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月20日 上午10:46:48
 * 
 */
public interface ParkMapper {

	void addBatch(@Param("parks") List<Park> parks);

	/**
	 * 
	 * @Title: getListParks
	 * @Description: 导出报表
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	List<Park> getListParks(ParkPageParam param);

	/**
	 * @Title: getBindPark
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param openid
	 * @return
	 */
	List<Park> getBindPark(@Param("openid") String openid);

	/**
	 * @Title: getParksByParkNo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parkNo
	 * @return
	 */
	Park getParksByParkNo(@Param("parkNo") String parkNo);

	/**
	 * @Title: getParksByHouseNo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param houseNo
	 * @return
	 */
	List<Park> getParksByHouseNo(@Param("houseNo") String houseNo);

	/**
	 * @Title: updateDueTime
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param dueTime
	 * @param relationId
	 */
	void updateDueTime(@Param("dueTime") String dueTime, @Param("id") Integer id);

	/**
	 * @Title: isExist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parkNo
	 * @param id
	 * @return
	 */
	Integer isExist(@Param("parkNo") String parkNo, @Param("id") Integer id);

	/**
	* @Title: addData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param park
	*/
	void addData(Park park);

	/**
	* @Title: updateData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param park
	*/
	void updateData(Park park);

	/**
	* @Title: delData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	*/
	void delData(@Param("id")Integer id);

	/**
	* @Title: detailData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	Park detailData(@Param("id")Integer id);

	/**
	* @Title: pageData
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param param
	* @return
	*/
	Page<Park> pageData(ParkPageParam param);

	/**
	* @Title: getListParksByUserId
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @return
	*/
	List<Park> getListParksByUserId(@Param("userId")Integer userId);

	/**
	* @Title: queryParkByCarNo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param carNo
	* @return
	*/
	List<Park> queryParkByCarNo(@Param("carNo")String carNo);
	
	List<ParkM> queryExpirePark();
}
