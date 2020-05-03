/**   
* @Title: LiveServeMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月11日 下午2:46:46 
* @version V1.0   
*/
package com.wuguan.huate.db;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.LiveServe;
import com.wuguan.huate.bean.params.LiveServeParam;

/**
 * @ClassName: LiveServeMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月11日 下午2:46:46
 * 
 */
public interface LiveServeMapper {

	/**
	 * @Title: isUse
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	Integer isUse(@Param("id") Integer id);

	/**
	 * @Title: isExist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param name
	 * @param id
	 * @return
	 */
	Integer isExist(@Param("name") String name, @Param("id") Integer id);

	/**
	 * @Title: addData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param liveServe
	 */
	void addData(LiveServe liveServe);

	/**
	 * @Title: updateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param liveServe
	 */
	void updateData(LiveServe liveServe);

	/**
	 * @Title: detailData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	LiveServe detailData(@Param("id") Integer id);

	/**
	 * @Title: pageData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param params
	 * @return
	 */
	Page<LiveServe> pageData(LiveServeParam params);

}
