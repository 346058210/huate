/**   
* @Title: UserParkMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年3月24日 下午7:06:18 
* @version V1.0   
*/
package com.wuguan.huate.db;

import org.apache.ibatis.annotations.Param;

/**
 * @ClassName: UserParkMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年3月24日 下午7:06:18
 * 
 */
public interface UserParkMapper {
	Integer isUse(@Param("userId") Integer userId);

	Integer isExist(@Param("parkId") Integer parkId);

	/**
	 * @Title: unboundPark
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @param id2
	 */
	void unboundPark(@Param("userId") Integer userId, @Param("parkId") Integer parkId);

	/**
	 * @Title: bindPark
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @param id2
	 */
	void bindPark(@Param("userId") Integer userId, @Param("parkId") Integer parkId);

	/**
	* @Title: queryOne
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param id
	* @param id2
	* @return
	*/
	Integer queryOne(@Param("userId") Integer userId, @Param("parkId") Integer parkId);

}
