/**   
* @Title: BasicMapper.java 
* @Package com.wuguan.huate.db 
* @Description: TODO(用一句话描述该文件做什么) 
* @author LiuYanHong
* @date 2020年4月8日 下午7:42:57 
* @version V1.0   
*/
package com.wuguan.huate.db;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuguan.huate.bean.entity.Basic;
import com.wuguan.huate.bean.vo.BasicVo;

/**
 * @ClassName: BasicMapper
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author LiuYanHong
 * @date 2020年4月8日 下午7:42:57
 * 
 */
public interface BasicMapper {

	/**
	 * @Title: addData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param basic
	 */
	void addData(Basic basic);

	/**
	 * @Title: updateData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param basic
	 */
	void updateData(Basic basic);

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
	BasicVo detailData(@Param("id") Integer id);

	/**
	 * @Title: queryData
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param company
	 * @return
	 */
	List<BasicVo> queryData(@Param("company") String company);

	/**
	 * @Title: isExist
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param company
	 * @param id
	 * @return
	 */
	Integer isExist(@Param("company") String company, @Param("id") Integer id);

}
