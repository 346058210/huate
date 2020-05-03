/**   
* @Title: AdvertisingMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月9日 下午6:00:46 
* @version V1.0   
*/
package com.wuguan.huate.db;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.Advertising;
import com.wuguan.huate.bean.params.AdvertisingPageParam;

/**
 * @ClassName: AdvertisingMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月9日 下午6:00:46
 * 
 */
public interface AdvertisingMapper {

	/**
	 * @Title: addData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param advertising
	 */
	void addData(Advertising advertising);

	/**
	 * @Title: updateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param advertising
	 */
	void updateData(Advertising advertising);

	/**
	 * @Title: delData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 */
	void delData(@Param("id") Integer id);

	/**
	 * @Title: detailData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	Advertising detailData(@Param("id") Integer id);

	/**
	 * @Title: pageData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param param
	 * @return
	 */
	Page<Advertising> pageData(AdvertisingPageParam param);

}
