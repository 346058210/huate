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
import com.wuguan.huate.bean.entity.Park;

/**
 * @ClassName: ParkMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月20日 上午10:46:48
 * 
 */
public interface ParkMapper {

	/**
	 * @Title: getParksByParkNo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param parkNo
	 * @return
	 */
	Park getParksByParkNo(@Param("parkNo") String parkNo);

	/**
	 * @Title: queryParkByCarNo
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param carNo
	 * @return
	 */
	List<Park> queryParkByCarNo(@Param("carNo") String carNo);

	/**
	 * @Title: detailData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	Object detailData(@Param("id") Integer id);
}
