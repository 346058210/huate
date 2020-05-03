/**   
* @Title: InformationMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月10日 下午3:11:59 
* @version V1.0   
*/
package com.wuguan.huate.db;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.wuguan.huate.bean.entity.Information;
import com.wuguan.huate.bean.params.InformationPageParam;

/**
 * @ClassName: InformationMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月10日 下午3:11:59
 * 
 */
public interface InformationMapper {

	/**
	 * @Title: addData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param information
	 */
	void addData(Information information);

	/**
	 * @Title: updateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param information
	 */
	void updateData(Information information);

	/**
	 * @Title: detailData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param id
	 * @return
	 */
	Information detailData(@Param("id") Integer id);

	/**
	 * @Title: pageData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param params
	 * @return
	 */
	Page<Information> pageData(InformationPageParam params);

	/**
	 * @Title: isExist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param title
	 * @param id
	 * @return
	 */
	Integer isExist(@Param("title") String title, @Param("id") Integer id);

}
